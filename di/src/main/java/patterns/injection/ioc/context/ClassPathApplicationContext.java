package patterns.injection.ioc.context;


import patterns.injection.ioc.context.cast.JavaNumberTypeCast;
import patterns.injection.ioc.entity.Bean;
import patterns.injection.ioc.entity.BeanDefinition;
import patterns.injection.ioc.exception.MultipleBeansForClassException;
import patterns.injection.ioc.io.BeanDefinitionReader;
import patterns.injection.ioc.io.XMLBeanDefinitionReader;

import java.util.*;

public class ClassPathApplicationContext implements ApplicationContext {
    private static final String SETTER_PREFIX = "set";
    private static final int SETTER_PARAMETER_INDEX = 0;

    private Map<String, Bean> beans;
    private BeanDefinitionReader beanDefinitionReader;

    public ClassPathApplicationContext() {

    }

    public ClassPathApplicationContext(String... path) {
        setBeanDefinitionReader(new XMLBeanDefinitionReader(path));
        start();
    }

    public void start() {
        beans = new HashMap<>();
        List<BeanDefinition> beanDefinitions = beanDefinitionReader.getBeanDefinitions();
        instantiateBeans(beanDefinitions);
        injectValueDependencies(beanDefinitions);
        injectRefDependencies(beanDefinitions);
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        List<T> matches = beans.values().stream()
                .map(Bean::getValue)
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .toList();

        if (matches.isEmpty()) {
            throw new RuntimeException("No bean found for class " + clazz);
        }
        if (matches.size() > 1) {
            throw new MultipleBeansForClassException("Multiple beans found for class " + clazz);
        }
        return matches.get(0);
    }

    @Override
    public <T> T getBean(String name, Class<T> clazz) {
        Bean bean = beans.get(name);
        if (bean == null) {
            throw new RuntimeException("No bean found with name " + name);
        }
        Object value = bean.getValue();
        if (!clazz.isInstance(value)) {
            throw new RuntimeException("Bean " + name + " is not of type " + clazz);
        }
        return clazz.cast(value);
    }

    @Override
    public <T> T getBean(String name) {
        Bean bean = beans.get(name);
        if (bean == null) {
            throw new RuntimeException("No bean found with name " + name);
        }
        return (T) bean.getValue();
    }

    @Override
    public void setBeanDefinitionReader(BeanDefinitionReader beanDefinitionReader) {
        this.beanDefinitionReader = beanDefinitionReader;
    }

    private void instantiateBeans(List<BeanDefinition> beanDefinitions) {
        for (BeanDefinition def : beanDefinitions) {
            try {
                Class<?> clazz = Class.forName(def.getBeanClassName());
                Object instance = clazz.getDeclaredConstructor().newInstance();
                beans.put(def.getId(), new Bean(def.getId(), instance));
            } catch (Exception e) {
                throw new RuntimeException("Failed to instantiate bean: " + def.getId(), e);
            }
        }
    }

    private void injectValueDependencies(List<BeanDefinition> beanDefinitions) {
        for (BeanDefinition def : beanDefinitions) {
            Bean bean = beans.get(def.getId());
            if (bean == null) continue;

            def.getDependencies().forEach((property, value) -> {
                try {
                    Object instance = bean.getValue();
                    String setterName = getSetterName(property);
                    Class<?> clazz = instance.getClass();

                    // Find setter method with single parameter, regardless of type
                    java.lang.reflect.Method setter = null;
                    for (var method : clazz.getMethods()) {
                        if (method.getName().equals(setterName) && method.getParameterCount() == 1) {
                            setter = method;
                            break;
                        }
                    }
                    if (setter == null) {
                        throw new NoSuchMethodException("No setter found for " + setterName);
                    }

                    Class<?> paramType = setter.getParameterTypes()[0];
                    Object convertedValue = convertValue(value, paramType);
                    setter.invoke(instance, convertedValue);

                } catch (Exception e) {
                    throw new RuntimeException("Failed to inject value dependency: " + property + " for bean: " + def.getId(), e);
                }
            });
        }
    }

    private Object convertValue(Object value, Class<?> targetType) {
        String stringValue = value.toString();

        if (targetType == String.class) {
            return stringValue;
        }
        Object casted = JavaNumberTypeCast.castPrimitive(stringValue, targetType);
        if (casted != null) {
            return casted;
        }
        if (Enum.class.isAssignableFrom(targetType)) {
            return Enum.valueOf((Class<Enum>) targetType, stringValue);
        }

        throw new IllegalArgumentException("Unsupported parameter type for setter: " + targetType);
    }

    private void injectRefDependencies(List<BeanDefinition> beanDefinitions) {
        for (BeanDefinition def : beanDefinitions) {
            Bean bean = beans.get(def.getId());
            if (bean == null) continue;

            def.getRefDependencies().forEach((property, refBeanId) -> {
                try {
                    Object instance = bean.getValue();
                    Object refBeanInstance = beans.get(refBeanId).getValue();
                    String setterName = getSetterName(property);
                    Class<?> clazz = instance.getClass();
                    var setter = findSetter(clazz, setterName, refBeanInstance.getClass());
                    setter.invoke(instance, refBeanInstance);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to inject ref dependency: " + property + " for bean: " + def.getId(), e);
                }
            });
        }
    }

    private java.lang.reflect.Method findSetter(Class<?> clazz, String setterName, Class<?> paramType) throws NoSuchMethodException {
        try {
            return clazz.getMethod(setterName, paramType);
        } catch (NoSuchMethodException e) {
            for (var method : clazz.getMethods()) {
                if (method.getName().equals(setterName) && method.getParameterCount() == 1 && method.getParameters()[0].getType().isAssignableFrom(paramType)) {
                    return method;
                }
            }
            throw e;
        }
    }

    private String getSetterName(String propertyName) {
        return SETTER_PREFIX + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
    }
}

package patterns.injection.factory;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import patterns.injection.exception.ConstructorException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.lang.reflect.Proxy.newProxyInstance;

public class ServiceBuildFactory
        implements AbstractFactory {

    private final Map<Class<?>, Object> services = new HashMap<>();

    Set<Class<?>> collect;

    public ServiceBuildFactory() {
        Reflections reflections = new Reflections("com.ua.pattern.di",
                new SubTypesScanner(false));
        collect = new HashSet<>(reflections.getSubTypesOf(Object.class));
        for (Class<?> clazz : collect) {
            if (clazz.isInterface()) {
                System.out.println("Found interface: " + clazz.getName());
            }
        }
    }

    public void build(Set<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            if (clazz.isInterface()) {
                Object proxyInstance = newProxyInstance(clazz.getClassLoader(),
                        new Class[]{clazz},
                        (proxy, method, args) -> method.invoke(proxy, args));
                services.put(clazz, proxyInstance);
            }
        }
    }

    public Set<Class<?>> getServices() {
        return collect;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T create(Class<T> clazz) {
        if (!clazz.isInterface()) {
            T service = (T) services.get(clazz);
            if (service != null) {
                return service;
            }
        }
        throw new ConstructorException("Service not found");
    }
}

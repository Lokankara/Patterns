package patterns.injection.ioc.entity;

public interface BeanPostProcessor {
    Object postProcessAfterInitialization(Object bean, String beanName);
}

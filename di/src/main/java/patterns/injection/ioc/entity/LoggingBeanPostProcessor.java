package patterns.injection.ioc.entity;

public class LoggingBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("Initialized bean: " + beanName + " of type " + bean.getClass().getSimpleName());
        return bean;
    }
}

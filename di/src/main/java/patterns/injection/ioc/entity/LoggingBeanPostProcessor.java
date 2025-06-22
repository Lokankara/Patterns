package patterns.injection.ioc.entity;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        log.info("Initialized bean: {} of type {}", beanName, bean.getClass().getSimpleName());
        return bean;
    }
}

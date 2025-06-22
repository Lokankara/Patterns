package patterns.proxy.transaction;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionalBeanPostProcessor implements BeanPostProcessor {

    private final TransactionalProxyFactory proxyFactory;

    @Override
    public Object postProcessAfterInitialization(Object bean, @NonNull String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(ProxyTransactional.class)) {
            Class<?>[] interfaces = beanClass.getInterfaces();
            if (interfaces.length == 0) return bean;
            return proxyFactory.createProxy(bean, interfaces[0]);
        }
        return bean;
    }
}

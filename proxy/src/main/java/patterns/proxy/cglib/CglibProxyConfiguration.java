package patterns.proxy.cglib;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import patterns.proxy.customer.CustomerService;
import patterns.proxy.customer.InterfaceCustomerService;
import patterns.proxy.jdbc.JdbcCustomerTemplate;

@Slf4j
@Configuration
@RequiredArgsConstructor
class CglibProxyConfiguration {

    private final CglibProxyFactory proxyFactory;

    @Bean
    public CustomerService interfaceCustomerService(JdbcCustomerTemplate customerTemplate) {
        return new InterfaceCustomerService(customerTemplate);
    }

    @Bean
    @Primary
    public CglibCustomerService cglibCustomerService(@Qualifier("interfaceCustomerService") CustomerService customerService) {
        return proxyFactory.createProxy(new CglibCustomerService(customerService));
    }

    @Bean
    ApplicationRunner interfaceDemo(CustomerService customerService) {
        return args -> customerService.create();
    }

    @Bean
    static CglibBPP cglibBPP() {
        return new CglibBPP();
    }

    static class CglibBPP implements SmartInstantiationAwareBeanPostProcessor {

        @Override
        public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
            if (bean instanceof CglibCustomerService) {
                try {
                    return cglib(bean, bean.getClass()).getProxy(bean.getClass().getClassLoader());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return SmartInstantiationAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
        }

        private static ProxyFactory cglib(Object target, Class<?> targetClass) {
            var pf = new ProxyFactory();
            pf.setTargetClass(targetClass);
            pf.setInterfaces(targetClass.getInterfaces());
            pf.setProxyTargetClass(true);
            pf.addAdvice((MethodInterceptor) invocation -> {
                log.info("before {}", invocation.getMethod().getName());
                var result = invocation.proceed();
                log.info("after {}", invocation.getMethod().getName());
                return result;
            });
            if (target != null) {
                pf.setTarget(target);
            }
            return pf;
        }

    }
}

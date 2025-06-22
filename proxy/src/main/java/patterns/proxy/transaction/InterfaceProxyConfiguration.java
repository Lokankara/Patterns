package patterns.proxy.transaction;

import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.aot.BeanFactoryInitializationAotContribution;
import org.springframework.beans.factory.aot.BeanFactoryInitializationAotProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import patterns.proxy.customer.InterfaceCustomerService;
import patterns.proxy.jdbc.JdbcCustomerTemplate;
import patterns.proxy.customer.CustomerService;

@Configuration
@RequiredArgsConstructor
class InterfaceProxyConfiguration {

    private final Transactions transactions;

    <T> T jdk(T target) throws Exception {
        var pfb = new ProxyFactoryBean();
        pfb.setProxyInterfaces(target.getClass().getInterfaces());
        pfb.setTarget(target);
        pfb.addAdvice((MethodInterceptor) invocation -> {
            try {
                transactions.handleTxStartFor(invocation.getMethod());
                return invocation.proceed();
            } finally {
                transactions.handleTxStopFor(invocation.getMethod());
            }
        });
        return (T) pfb.getObject();
    }

    @Bean
    ApplicationRunner interfaceDemo(CustomerService customerService) {
        return args -> customerService.create();
    }

    @Bean
    InterfaceCustomerService interfaceCustomerService(JdbcCustomerTemplate customerTemplate) {
        return new InterfaceCustomerService(customerTemplate);
    }

    @Bean
    static InterfaceBPP interfaceBPP(Transactions transactions) {
        return new InterfaceBPP(transactions);
    }

    @Bean
    static InterfaceBFIAP interfaceBFIAP() {
        return new InterfaceBFIAP();
    }

    static class InterfaceBPP implements BeanPostProcessor {

        private final Transactions transactions;

        public InterfaceBPP(Transactions transactions) {
            this.transactions = transactions;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof InterfaceCustomerService service) {
                try {
                    return new InterfaceProxyConfiguration(transactions).jdk(service);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return bean;
        }
    }

    static class InterfaceBFIAP implements BeanFactoryInitializationAotProcessor {
        @Override
        public BeanFactoryInitializationAotContribution processAheadOfTime(ConfigurableListableBeanFactory beanFactory) {
            return (generationContext, code) -> generationContext.getRuntimeHints()
                    .proxies().registerJdkProxy(
                            CustomerService.class,
                            org.springframework.aop.SpringProxy.class,
                            org.springframework.aop.framework.Advised.class,
                            org.springframework.core.DecoratingProxy.class);
        }
    }
}

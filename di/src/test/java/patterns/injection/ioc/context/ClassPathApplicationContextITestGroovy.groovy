package patterns.injection.ioc.context

import patterns.injection.ioc.entity.LoggingBeanPostProcessor
import patterns.injection.ioc.exception.MultipleBeansForClassException
import patterns.injection.ioc.io.XMLBeanDefinitionReader
import patterns.injection.ioc.service.MailService
import patterns.injection.ioc.service.PaymentService
import patterns.injection.ioc.service.UserService
import spock.lang.Specification

class ClassPathApplicationContextITestGroovy extends Specification {

    UserService userService
    MailService mailService
    PaymentService paymentService
    PaymentService paymentServiceWithMaxAmount
    ApplicationContext applicationContext

    def setup() {
        mailService = new MailService(protocol: "POP3", port: 3000)
        userService = new UserService(mailService: mailService)
        paymentService = new PaymentService(mailService: mailService)
        paymentServiceWithMaxAmount = new PaymentService(mailService: mailService, maxAmount: 500)
        applicationContext = new ClassPathApplicationContext("src/test/resources/context.xml")
        applicationContext.addBeanPostProcessor(new LoggingBeanPostProcessor())
    }

    def "post processor modifies MailService after initialization"() {
        given:
        def processor = new LoggingBeanPostProcessor() {
            @Override
            Object postProcessAfterInitialization(Object bean, String beanName) {
                if (bean instanceof MailService && beanName == "mailService") {
                    bean.setProtocol("SMTP")
                }
                return bean
            }
        }

        and:
        def context = new ClassPathApplicationContext("src/test/resources/context.xml")
        context.addBeanPostProcessor(processor)

        when:
        context.start()
        MailService processedMailService = context.getBean("mailService", MailService)

        then:
        processedMailService.getProtocol() == "SMTP"
    }

    def "post processor returns proxy instead of original bean"() {
        given:
        def proxyInstance = new MailService()
        proxyInstance.setProtocol("PROXY")

        def processor = new LoggingBeanPostProcessor() {
            @Override
            Object postProcessAfterInitialization(Object bean, String beanName) {
                if (beanName == "mailService") {
                    return proxyInstance
                }
                return bean
            }
        }

        and:
        def context = new ClassPathApplicationContext("src/test/resources/context.xml")
        context.addBeanPostProcessor(processor)

        when:
        context.start()
        MailService returned = context.getBean("mailService", MailService)

        then:
        returned.is(proxyInstance)
        returned.getProtocol() == "PROXY"
    }

    def "post processor is called for every bean in context"() {
        given:
        def invokedBeans = []

        def processor = new LoggingBeanPostProcessor() {
            @Override
            Object postProcessAfterInitialization(Object bean, String beanName) {
                invokedBeans << beanName
                return bean
            }
        }

        and:
        def context = new ClassPathApplicationContext("src/test/resources/context.xml")
        context.addBeanPostProcessor(processor)

        when:
        context.start()

        then:
        invokedBeans.containsAll(["mailService", "userService", "paymentService", "paymentWithMaxService"])
    }

    def "application context instantiation with set bean definition reader returns consistent beans"() {
        given:
        ApplicationContext applicationContextSetReader = new ClassPathApplicationContext()
        applicationContextSetReader.setBeanDefinitionReader(new XMLBeanDefinitionReader("src/test/resources/context.xml"))

        when:
        applicationContextSetReader.start()

        then:
        applicationContext.getBean(UserService.class) == applicationContextSetReader.getBean(UserService.class)
        applicationContext.getBean("mailService", MailService.class) == applicationContextSetReader.getBean("mailService", MailService.class)
        applicationContext.getBean("paymentWithMaxService") == applicationContextSetReader.getBean("paymentWithMaxService")
    }

    def "get bean by class returns correct bean instance"() {
        expect:
        userService == applicationContext.getBean(UserService.class)
        mailService == applicationContext.getBean(MailService.class)
    }

    def "get bean by class throws MultipleBeansForClassException when multiple beans exist"() {
        when:
        applicationContext.getBean(PaymentService.class)

        then:
        thrown(MultipleBeansForClassException)
    }

    def "get bean by name and class returns correct bean instance"() {
        expect:
        userService == applicationContext.getBean("userService", UserService.class)
        mailService == applicationContext.getBean("mailService", MailService.class)
        paymentService == applicationContext.getBean("paymentService", PaymentService.class)
        paymentServiceWithMaxAmount == applicationContext.getBean("paymentWithMaxService", PaymentService.class)
    }

    def "get bean by name returns correct bean instance"() {
        expect:
        userService == applicationContext.getBean("userService")
        mailService == applicationContext.getBean("mailService")
        paymentService == applicationContext.getBean("paymentService")
        paymentServiceWithMaxAmount == applicationContext.getBean("paymentWithMaxService")
    }
}

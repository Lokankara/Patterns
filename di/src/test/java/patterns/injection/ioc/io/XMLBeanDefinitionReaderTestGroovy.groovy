package patterns.injection.ioc.io

import patterns.injection.ioc.entity.BeanDefinition
import patterns.injection.ioc.exception.SourceParseException
import spock.lang.Specification

class XMLBeanDefinitionReaderTestGroovy extends Specification {

    List<BeanDefinition> expectedBeanDefinitions

    def setup() {
        expectedBeanDefinitions = []
        expectedBeanDefinitions.add(new BeanDefinition(
                id: "mailService",
                beanClassName: "patterns.injection.ioc.service.MailService",
                dependencies: ["protocol": "POP3", "port": "3000"]
        ))

        expectedBeanDefinitions.add(new BeanDefinition(
                id: "userService",
                beanClassName: "patterns.injection.ioc.service.UserService",
                refDependencies: ["mailService": "mailService"]
        ))

        expectedBeanDefinitions.add(new BeanDefinition(
                id: "paymentService",
                beanClassName: "patterns.injection.ioc.service.PaymentService",
                refDependencies: ["mailService": "mailService"]
        ))

        expectedBeanDefinitions.add(new BeanDefinition(
                id: "paymentWithMaxService",
                beanClassName: "patterns.injection.ioc.service.PaymentService",
                dependencies: ["maxAmount": "500"],
                refDependencies: ["mailService": "mailService"]
        ))
    }

    def "test getBeanDefinitions returns expected beans"() {
        given:
        def xmlBeanDefinitionReader = new XMLBeanDefinitionReader("src/test/resources/context.xml")

        when:
        def actualBeanDefinitions = xmlBeanDefinitionReader.getBeanDefinitions()

        then:
        expectedBeanDefinitions.every { expected ->
            actualBeanDefinitions.remove(expected)
        }
        actualBeanDefinitions.isEmpty() // Ensure no unexpected beans remain
    }

    def "test getBeanDefinitions throws exception on invalid XML"() {
        when:
        new XMLBeanDefinitionReader("src/test/resources/context-exception.xml").getBeanDefinitions()

        then:
        thrown(SourceParseException)
    }
}

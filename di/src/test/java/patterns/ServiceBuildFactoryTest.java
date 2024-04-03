package patterns;

import org.junit.jupiter.api.Test;
import patterns.injection.exception.ConstructorException;
import patterns.injection.exception.NonExistentService;
import patterns.injection.factory.AbstractFactory;
import patterns.injection.factory.DIFactory;
import patterns.injection.factory.ServiceBuildFactory;
import patterns.injection.service.DBIntegrationService;
import patterns.injection.service.FileIntegrationService;
import patterns.injection.service.StringIntegrationService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ServiceBuildFactoryTest {

    void testServicesCreations() {
        ServiceBuildFactory buildFactory = new ServiceBuildFactory();
        Set<Class<?>> services = buildFactory.getServices();

        for (Class<?> service : services) {
            Object createdService = buildFactory.create(service);
            assertNotNull(createdService, "Service not found for: " + service);
        }
    }

    void testServiceInstantiations() {
        ServiceBuildFactory buildFactory = new ServiceBuildFactory();
        Set<Class<?>> services = buildFactory.getServices();

        for (Class<?> clazz : services) {
            if (!clazz.isInterface()) {
                assertDoesNotThrow(() -> {
                    Object service = clazz.getDeclaredConstructor()
                                          .newInstance();
                }, "Failed to instantiate service: " + clazz);
            }
        }
    }

    @Test
    void testServiceInstantiation() {
        ServiceBuildFactory buildFactory = new ServiceBuildFactory();
        Set<Class<?>> services = buildFactory.getServices();
        buildFactory.build(services);
        for (Class<?> clazz : services) {
            if (!clazz.isInterface()) {
                assertDoesNotThrow(() -> buildFactory.create(clazz));
            }
        }
    }

    @Test
    void testServicesCreation() {
        ServiceBuildFactory buildFactory = new ServiceBuildFactory();
        Set<Class<?>> services = buildFactory.getServices();
        assertEquals(0, services.size());
    }

    @Test
    void testServiceNotFound() {
        ServiceBuildFactory buildFactory = new ServiceBuildFactory();
        assertThrows(ConstructorException.class,
                     () -> buildFactory.create(NonExistentService.class));
    }

    @Test
    void testStringIntegrationService() {
        AbstractFactory factory = new DIFactory();
        StringIntegrationService service =
                factory.create(StringIntegrationService.class);
        String regex = service.parseStringByRegex("Alice;Bob;Charlie");
        assertEquals("Alice Bob Charlie", regex);
    }

    @Test
    void testFileIntegrationService() {
        String path = "src\\main\\resources\\log4j2.xml";
        AbstractFactory factory = new DIFactory();
        FileIntegrationService service =
                factory.create(FileIntegrationService.class);
        String fileNameByPath = service.getFileNameByPath(path);
        assertNotNull(fileNameByPath);
    }

    @Test
    void testDBIntegrationService() {
        AbstractFactory factory = new DIFactory();
        DBIntegrationService service =
                factory.create(DBIntegrationService.class);

        String book = service.getBookByYearAndAuthor("1984", "Orwell");
        assertEquals(
                "SELECT * FROM book WHERE year = '1984' AND author = 'Orwell'",
                book);

        String author = service.getBookByYearOrAuthor("1984", "Orwell");
        assertEquals(
                "SELECT * FROM book WHERE year = '1984' OR author = 'Orwell'",
                author);
    }
}

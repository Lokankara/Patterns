package patterns.injection;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import lombok.extern.slf4j.Slf4j;
import patterns.injection.factory.AbstractFactory;
import patterns.injection.factory.DIFactory;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class AppDI {

    public static void main(String[] args) {

        AbstractFactory abstractFactory = new DIFactory();
        Set<Class<?>> collect;

        Reflections reflections = new Reflections("com.ua.pattern.di",
                new SubTypesScanner(false));
        collect = new HashSet<>(reflections.getSubTypesOf(Object.class));
        for (Class<?> clazz : collect) {
            if (clazz.isInterface()) {
                log.info("Found interface: {}", clazz.getName());
                Object object = abstractFactory.create(clazz);
                log.info(String.valueOf(object));
            }
        }
    }
}

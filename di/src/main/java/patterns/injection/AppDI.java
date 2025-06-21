package patterns.injection;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import patterns.injection.factory.AbstractFactory;
import patterns.injection.factory.DIFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AppDI {

    private static Map<Class<?>, Object> services = new HashMap<>();
    public static void main(String[] args) {

//        ServiceBuildFactory buildFactory = new ServiceBuildFactory();
//        Set<Class<?>> services = buildFactory.getServices();

        AbstractFactory abstractFactory = new DIFactory();
        Set<Class<?>> collect;

        Reflections reflections = new Reflections("com.ua.pattern.di",
                new SubTypesScanner(false));
        collect = new HashSet<>(reflections.getSubTypesOf(Object.class));
        for (Class<?> clazz : collect) {
            if (clazz.isInterface()) {
                System.out.println("Found interface: " + clazz.getName());
                Object object = abstractFactory.create(clazz);
                System.out.println(object);
            }
        }


//        for (Class<?> clazz : collect) {
//            if (!clazz.isInterface()) {
//                try {
//                    Object service = clazz.getDeclaredConstructor().newInstance();
//                    System.out.println("Service class found: " + service);
//                } catch (Exception e) {
//                    System.err.println(clazz);
//                }
//            } else {
//                System.out.println("Interface class found: " + clazz.getName());
//                Object object = abstractFactory.create(clazz);
//                System.out.println(object.getClass());
//            }
//        }
    }
}

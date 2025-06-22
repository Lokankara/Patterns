package patterns.proxy.cglib;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.stereotype.Component;
import patterns.proxy.transaction.Transactions;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Comparator;


@Component
@RequiredArgsConstructor
public class CglibProxyFactory {
    private final Transactions transactions;

    public <T> T createProxy(T target) {
        Class<?> targetClass = target.getClass();
        try {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(targetClass);
            enhancer.setCallbackType(MethodInterceptor.class);

            Class<?> proxyClass = enhancer.createClass();
            Enhancer.registerStaticCallbacks(proxyClass, new Callback[]{
                    (MethodInterceptor) (obj, method, args, proxy) -> {
                        transactions.handleTxStartFor(method);
                        try {
                            return proxy.invoke(target, args);
                        } finally {
                            transactions.handleTxStopFor(method);
                        }
                    }
            });

            Constructor<?> constructor = findPrimaryConstructor(targetClass);
            Object[] args = getConstructorArguments(target, constructor);
            return (T) constructor.newInstance(args);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create proxy", e);
        }
    }

    private Constructor<?> findPrimaryConstructor(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredConstructors())
                .max(Comparator.comparingInt(Constructor::getParameterCount))
                .orElseThrow(() -> new IllegalStateException("No constructors found"));
    }

    private Object[] getConstructorArguments(Object target, Constructor<?> constructor) {
        return Arrays.stream(constructor.getParameterTypes())
                .map(paramType -> findFieldValue(target, paramType))
                .toArray();
    }

    private Object findFieldValue(Object target, Class<?> paramType) {
        return Arrays.stream(target.getClass().getDeclaredFields())
                .filter(f -> f.getType().equals(paramType))
                .findFirst()
                .map(field -> {
                    field.setAccessible(true);
                    try {
                        return field.get(target);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Failed to access field", e);
                    }
                })
                .orElseThrow(() -> new IllegalStateException("No matching field found for type: " + paramType));
    }
}
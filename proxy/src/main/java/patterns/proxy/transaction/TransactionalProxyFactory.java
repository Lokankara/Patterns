package patterns.proxy.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class TransactionalProxyFactory {

    private final ProxyTransactionManager txManager;

    @SuppressWarnings("unchecked")
    public <T> T createProxy(Object target, Class<?> interfaceType) {
        return (T) Proxy.newProxyInstance(
                interfaceType.getClassLoader(),
                new Class[]{interfaceType},
                (proxy, method, args) -> {
                    Connection connection = txManager.getConnection();
                    try {
                        txManager.begin(connection);
                        try {
                            Object result = method.invoke(target, args);
                            txManager.commit(connection);
                            return result;
                        } catch (InvocationTargetException ex) {
                            txManager.rollback(connection);
                            Throwable cause = ex.getCause();
                            if (cause instanceof RuntimeException) {
                                throw cause;
                            } else if (cause instanceof Error) {
                                throw cause;
                            } else {
                                throw new DataException("Transactional proxy failed");
                            }
                        }
                    } catch (SQLException e) {
                        txManager.rollback(connection);
                        throw new DataException("Transaction failed", e);
                    }
                });
    }
}

package patterns.proxy.transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@Component
@RequiredArgsConstructor
public class Transactions {

    private final ProxyTransactionManager txManager;

    public void handleTxStartFor(Method method) {
        if (method.isAnnotationPresent(ProxyTransactional.class) && !TransactionContext.isActive()) {
            try {
                Connection conn = txManager.getConnection();
                txManager.begin(conn);
                TransactionContext.set(conn);
                log.debug("Transaction started");
            } catch (SQLException e) {
                throw new DataException("Transaction start failed", e);
            }
        }
    }

    public void handleTxStopFor(Method method) {
        if (method.isAnnotationPresent(ProxyTransactional.class) && TransactionContext.isActive()) {
            Connection conn = TransactionContext.get();
            try {
                txManager.commit(conn);
                log.debug("Transaction committed");
            } catch (SQLException e) {
                txManager.rollback(conn);
                throw new DataException("Transaction failed", e);
            } finally {
                TransactionContext.clear();
            }
        }
    }
}

package patterns.proxy.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import patterns.proxy.customer.Customer;
import patterns.proxy.customer.CustomerResultSetMapper;
import patterns.proxy.customer.CustomerSQL;
import patterns.proxy.transaction.DataException;
import patterns.proxy.transaction.TransactionContext;
import patterns.proxy.transaction.ProxyTransactionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcCustomerTemplate implements Template<Customer> {

    private final ProxyTransactionManager proxyTransactionManager;
    private final CustomerResultSetMapper resultSetMapper;

    @Override
    public Optional<Customer> getByName(String name) {
        try (PreparedStatement stmt = getConnection().prepareStatement(CustomerSQL.SELECT_CUSTOMER_BY_NAME)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(resultSetMapper.mapRow(rs));
                }
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataException("Failed to get customer by name", e);
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(CustomerSQL.SELECT_ALL_CUSTOMERS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                customers.add(resultSetMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new DataException("Failed to get all customers", e);
        }
        return customers;
    }

    @Override
    public Customer save(Customer customer) {
        try (PreparedStatement stmt = getConnection().prepareStatement(CustomerSQL.INSERT_CUSTOMER)) {
            setCustomerParameters(stmt, customer);
            stmt.executeUpdate();
            return customer;
        } catch (SQLException e) {
            throw new DataException("Failed to save customer", e);
        }
    }

    @Override
    public List<Customer> saveAll(List<Customer> customers) {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(CustomerSQL.INSERT_CUSTOMER)) {
            connection.setAutoCommit(false);
            for (Customer customer : customers) {
                setCustomerParameters(stmt, customer);
                stmt.addBatch();
            }
            stmt.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
            return customers;
        } catch (SQLException e) {
            throw new DataException("Failed to save customers batch", e);
        }
    }

    private void setCustomerParameters(PreparedStatement stmt, Customer customer) throws SQLException {
        stmt.setObject(1, customer.getId());
        stmt.setString(2, customer.getLogin());
        stmt.setString(3, customer.getPassword());
        stmt.setString(4, customer.getEmail());
        stmt.setString(5, customer.getSalt());
        stmt.setBoolean(6, customer.isActive());
    }

    private Connection getConnection() {
        Connection txConn = TransactionContext.get();
        return txConn != null ? txConn : proxyTransactionManager.getConnection();
    }
}

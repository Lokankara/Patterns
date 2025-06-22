package patterns.web.repository.template;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import patterns.web.exception.DataException;
import patterns.web.model.entity.Customer;
import patterns.web.repository.provider.ProxyConnection;
import patterns.web.service.mapper.result.CustomerResultSetMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static patterns.web.repository.provider.DbManager.getConnection;

@Component
@RequiredArgsConstructor
public class JdbcCustomerTemplate implements Template<Customer> {

    public static final String INSERT_CUSTOMER =
            "INSERT INTO customers (id, login, password, email, salt, active) VALUES (?, ?, ?, ?, ?, ?)";

    public static final String SELECT_ALL_CUSTOMERS =
            "SELECT id, login, password, email, salt, active FROM customers";

    public static final String SELECT_CUSTOMER_BY_NAME =
            "SELECT id, login, password, email, salt, active FROM customers WHERE login = ?";

    private final CustomerResultSetMapper resultSetMapper;

    @Override
    public Optional<Customer> getByName(String name) {
        try (ProxyConnection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_CUSTOMER_BY_NAME)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? Optional.of(resultSetMapper.mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataException("Cannot find customer by name", e);
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> result = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(SELECT_ALL_CUSTOMERS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                result.add(resultSetMapper.mapRow(rs));
            }
            return result;
        } catch (SQLException e) {
            throw new DataException("Cannot load customers", e);
        }
    }

    @Override
    public Customer save(Customer customer) {
        try (ProxyConnection connection = getConnection()) {
            insertQuery(Collections.singletonList(customer), connection);
        } catch (SQLException e) {
            throw new DataException("Cannot save customer", e);
        }
        return customer;
    }

    @Override
    public List<Customer> saveAll(List<Customer> customers) {
        try (ProxyConnection connection = getConnection()) {
            insertQuery(customers, connection);
        } catch (SQLException e) {
            throw new DataException("Cannot save customer batch", e);
        }
        return customers;
    }

    private void insertQuery(Collection<Customer> customers, ProxyConnection connection) throws SQLException {
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_CUSTOMER)) {
            for (Customer customer : customers) {
                stmt.setObject(1, customer.getId());
                stmt.setString(2, customer.getLogin());
                stmt.setString(3, customer.getPassword());
                stmt.setString(4, customer.getEmail());
                stmt.setString(5, customer.getSalt());
                stmt.setBoolean(6, customer.isActive());
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            connection.rollback();
            throw new DataException("Insert failed", e);
        } finally {
            connection.setAutoCommit(true);
        }
    }
}

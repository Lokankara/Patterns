package patterns.proxy.customer;

public interface CustomerSQL {

    String INSERT_CUSTOMER = """
            INSERT INTO customers (id, login, password, email, salt, active)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    String SELECT_ALL_CUSTOMERS = """
            SELECT id, login, password, email, salt, active
            FROM customers
            """;

    String SELECT_CUSTOMER_BY_NAME = """
            SELECT id, login, password, email, salt, active
            FROM customers
            WHERE login = ?
            """;
}

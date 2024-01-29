CREATE TABLE customers
(
    customer_id INTEGER PRIMARY KEY,
    name        TEXT
);

CREATE TABLE customer_rental
(
    customer_id INTEGER,
    rental_id   INTEGER,
    FOREIGN KEY (customer_id) REFERENCES customers (customer_id),
    FOREIGN KEY (rental_id) REFERENCES rentals (rental_id)
);

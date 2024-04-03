package patterns.dto.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer
        implements Serializable {

    private Integer id;
    private String name;
    private String surname;
    private Long timestamp;
    private List<Ship> ships;

    public Customer(
            String name,
            String surname) {
        this.name = name;
        this.surname = surname;
        this.ships = new ArrayList<>();
        this.timestamp = Timestamp.valueOf(LocalDateTime.now()).getTime();
    }

    public Customer(
            Integer id,
            String name,
            String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now()).getTime();
    }

    public Customer() {

    }

    public void addShip(Ship ship) {
        if (this.ships == null) {
            this.ships = new ArrayList<>();
        }
        this.ships.add(ship);
    }

    public List<Ship> getShips() {
        return this.ships;
    }

    public Integer getId() {
        return id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "{surname=%s, name=%s, id=%s, timestamp=%d}"
                .formatted(surname, name, id, timestamp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name,
                customer.name) && Objects.equals(surname,
                customer.surname) && Objects.equals(ships, customer.ships);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, ships);
    }
}

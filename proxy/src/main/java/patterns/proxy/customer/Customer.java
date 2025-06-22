package patterns.proxy.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Value
@Builder
@Entity
@Getter
@Setter
@Table(name = "customers")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Customer {
    @Id
    Long id;
    String login;
    String password;
    String email;
    String salt;
    boolean active;
}

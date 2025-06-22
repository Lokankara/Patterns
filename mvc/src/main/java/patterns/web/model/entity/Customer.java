package patterns.web.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Long id;
    private String login;
    private String password;
    private String email;
    private String salt;
    private boolean active;
    private List<Rental> rentals;
}

package patterns.injection.ioc.service;


import lombok.Setter;

import java.util.Objects;

@Setter
public class UserService {
    private MailService mailService;

    public UserService() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserService that = (UserService) o;
        return Objects.equals(mailService, that.mailService);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mailService);
    }

}

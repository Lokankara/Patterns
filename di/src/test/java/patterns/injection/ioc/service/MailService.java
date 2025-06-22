package patterns.injection.ioc.service;


import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class MailService {
    private int port;
    private String protocol;

    public MailService() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailService that = (MailService) o;
        return port == that.port &&
                Objects.equals(protocol, that.protocol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(port, protocol);
    }
}

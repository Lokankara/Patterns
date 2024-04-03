package patterns.dto.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Login {

    private String url;
    private String loginName;
    private String password;
    private Long timestamp;
    private List<Role> roles = new ArrayList<>();
    private boolean paid;

    public Login() {
    }

    public Login(
            String url,
            String loginName,
            String password,
            Long timestamp) {
        this.url = url;
        this.loginName = loginName;
        this.password = password;
        this.timestamp = timestamp;
    }

    public Login(
            String loginName,
            String password) {
        this.loginName = loginName;
        this.password = password;
    }

    public Login(
            String name,
            List<Role> roles) {
        this.loginName = name;
        this.roles = roles;
    }

    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return loginName;
    }

    public void setLogin(String login) {
        this.loginName = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("{login: %s, password: %s, timestamp: %d}",
                             loginName,
                             password,
                             timestamp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Login that = (Login) o;
        return Objects.equals(loginName, that.loginName) && Objects.equals(
                password,
                that.password) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginName, password, timestamp);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public boolean isPaid() {
        return paid;
    }
}

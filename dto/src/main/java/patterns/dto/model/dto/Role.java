package patterns.dto.model.dto;

public enum Role {
    ADMIN("Administrator"),
    EDITOR("Editor"),
    USER("Regular User");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

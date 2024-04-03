package patterns.dto.model.dto;

public record LoginRecord(String login, String password, Long timestamp) {

    @Override
    public String toString() {
        return String.format("{login: %s, password: %s, timestamp: %d}",
                login, password, timestamp);
    }
}

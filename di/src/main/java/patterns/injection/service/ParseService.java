package patterns.injection.service;

public class ParseService
        implements StringIntegrationService {
    @Override
    public String parseStringByRegex(String value) {
        String[] split = value.split(";");
        return String.join(" ", split);
    }
}

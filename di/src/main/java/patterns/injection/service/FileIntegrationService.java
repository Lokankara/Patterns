package patterns.injection.service;

public interface FileIntegrationService {

    String getFileNameByPath(String path);

    String getDataFromPath(String path);
}

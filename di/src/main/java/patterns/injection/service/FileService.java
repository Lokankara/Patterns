package patterns.injection.service;

import patterns.injection.exception.FileException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileService
        implements FileIntegrationService {
    @Override
    public String getFileNameByPath(String path) {
        File file = new File(path);
        return file.getName();
    }

    public String getDataFromPath(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }
    }
}

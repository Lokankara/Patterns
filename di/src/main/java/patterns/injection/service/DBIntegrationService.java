package patterns.injection.service;

public interface DBIntegrationService {

    String getUserById(String id);

    String getCityByRegion(String name);

    String getCarByYear(String year);

    String getBookByYearAndAuthor(String year, String author);

    String getBookByYearOrAuthor(String year,String author);
}

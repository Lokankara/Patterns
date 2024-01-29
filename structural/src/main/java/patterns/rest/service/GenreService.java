package patterns.rest.service;

import patterns.rest.model.entity.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> saveAll(List<Genre> genres);
}

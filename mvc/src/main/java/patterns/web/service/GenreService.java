package patterns.web.service;

import patterns.web.model.entity.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> saveAll(List<Genre> genres);
}

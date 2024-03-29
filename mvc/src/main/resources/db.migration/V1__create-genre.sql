CREATE TABLE genres
(
    genre_id INTEGER PRIMARY KEY,
    name     TEXT NOT NULL
);

CREATE TABLE movie_genres
(
    movie_id INTEGER NOT NULL,
    genre_id INTEGER NOT NULL,
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES movies (movie_id),
    FOREIGN KEY (genre_id) REFERENCES genres (genre_id)
);

CREATE TABLE movies
(
    movie_id   INTEGER PRIMARY KEY,
    title      TEXT NOT NULL,
    review_id  INTEGER,
    genre_id   INTEGER,
    price_code TEXT NOT NULL
);

CREATE TABLE movie_review
(
    movie_id  INTEGER NOT NULL,
    review_id INTEGER NOT NULL,
    PRIMARY KEY (movie_id, review_id),
    FOREIGN KEY (movie_id) REFERENCES movies (movie_id),
    FOREIGN KEY (review_id) REFERENCES reviews (review_id)
);

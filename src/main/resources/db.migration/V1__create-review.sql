CREATE TABLE reviews
(
    review_id     INTEGER PRIMARY KEY,
    country       TEXT,
    overview      TEXT,
    director      TEXT,
    backdrop_path TEXT,
    rating        REAL
);

CREATE TABLE review_actors
(
    review_id INTEGER NOT NULL,
    actor_id  INTEGER NOT NULL,
    PRIMARY KEY (review_id, actor_id),
    FOREIGN KEY (review_id) REFERENCES reviews (review_id),
    FOREIGN KEY (actor_id) REFERENCES actors (actor_id)
);

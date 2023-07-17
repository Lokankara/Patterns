INSERT INTO genres (genre_id, name)
VALUES (1, 'Comedy'),
       (2, 'Fantasy'),
       (3, 'Action'),
       (4, 'Drama'),
       (5, 'Romance'),
       (6, 'Thriller'),
       (7, 'Adventure'),
       (8, 'Sci-Fi'),
       (9, 'History'),
       (10, 'Western'),
       (11, 'Music'),
       (12, 'Horror'),
       (13, 'Animation'),
       (14, 'Mystery');


INSERT INTO actors (actor_id, name)
VALUES (1, 'Sylvester Stallone'),
       (2, 'Richard Crenna'),
       (3, 'Elijah Wood'),
       (4, 'Ian McKellen'),
       (5, 'Daniel Radcliffe'),
       (6, 'Emma Watson');

INSERT INTO movies (movie_id, title, price_code)
VALUES (1, 'Rambo', 'REGULAR'),
       (2, 'Lord of the Rings', 'NEW_RELEASE'),
       (3, 'Harry Potter', 'CHILDREN');


INSERT INTO reviews (review_id, country, overview, director,
                            backdrop_path, rating)
VALUES (1, 'USA', 'An action-packed thriller', 'Sylvester Stallone',
        'pzPdwOitmTleVE3YPMfIQgLh84p.jpg', 8);
INSERT INTO reviews (review_id, country, overview, director,
                            backdrop_path, rating)
VALUES (2, 'NZ', 'An epic fantasy adventure', 'Peter Jackson',
        '6oom5QYQ2yQTMJIbnvbkBL9cHo6.jpg', 10);
INSERT INTO reviews (review_id, country, overview, director,
                            backdrop_path, rating)
VALUES (3, 'UK', 'A magical journey of friendship and bravery',
        'Chris Columbus', 'wuMc08IPKEatf9rnMNXvIDxqP4W.jpg', 9);

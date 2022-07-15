CREATE TABLE author (
                        id bigserial PRIMARY KEY,
                        last_name varchar(255),
                        first_name varchar(255),
                        patronymic varchar(255),
                        deleted boolean NOT NULL,
                        date_of_birth date,
                        date_of_creation timestamp,
                        date_of_modification timestamp
);

CREATE TABLE genre (
                       id bigserial PRIMARY KEY,
                       description varchar(255),
                       deleted boolean NOT NULL,
                       date_of_creation timestamp,
                       date_of_modification timestamp
);

CREATE TABLE book (
                      id bigserial PRIMARY KEY,
                      isbn varchar(30) UNIQUE ,
                      deleted boolean NOT NULL,
                      genre_id bigint REFERENCES genre(id),
                      date_of_creation timestamp,
                      date_of_modification timestamp
);

CREATE TABLE author_book (
                               id bigserial PRIMARY KEY,
                               author_id bigint REFERENCES author(id),
                               book_id bigint REFERENCES book(id)
);


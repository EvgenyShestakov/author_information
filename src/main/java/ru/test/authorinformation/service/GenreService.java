package ru.test.authorinformation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.test.authorinformation.domain.model.Genre;

public interface GenreService {
    Genre saveGenre(Genre genre);

    Genre updateGenre(Genre genre);

    void deleteGenre(Long id);

    Page<Genre> findAllGenres(Pageable pageable);

    Genre findGenreById(Long id);
}

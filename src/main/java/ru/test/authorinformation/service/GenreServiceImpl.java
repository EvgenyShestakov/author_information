package ru.test.authorinformation.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.authorinformation.domain.model.Genre;
import ru.test.authorinformation.repository.GenreRepository;
import ru.test.authorinformation.exeption.NotFoundInDataBaseException;

@Service
@Getter
@Transactional
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {
    private GenreRepository genreRepository;

    @Override
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre updateGenre(Genre genre) {
        Genre genreDb = genreRepository.findById(genre.getId()).
                orElseThrow(() -> new NotFoundInDataBaseException("Genre not found in database"));
        genre.setDateOfCreation(genreDb.getDateOfCreation());
        return genreRepository.save(genre);
    }

    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public Page<Genre> findAllGenres(Pageable pageable) {
        return genreRepository.findAll(pageable);
    }

    @Override
    public Genre findGenreById(Long id) {
        return genreRepository.findById(id).
                orElseThrow(() -> new NotFoundInDataBaseException("Genre not found in database"));
    }
}

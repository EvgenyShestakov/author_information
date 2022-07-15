package ru.test.authorinformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.authorinformation.domain.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}

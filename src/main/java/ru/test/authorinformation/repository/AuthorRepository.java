package ru.test.authorinformation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.test.authorinformation.domain.model.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(value = "select a from Author a join fetch a.books b join fetch b.genre",
            countQuery = "select count(a) from Author a")
    Page<Author> findAllAuthorsWithPagination(Pageable pageable);

    @Query(value = "select a from Author a join fetch a."
            + "books b join fetch b.genre where a.id = :id")
    Optional<Author> findAuthorById(@Param("id")Long id);
}

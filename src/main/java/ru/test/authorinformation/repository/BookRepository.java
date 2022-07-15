package ru.test.authorinformation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.test.authorinformation.domain.model.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "select b from Book b join fetch b.genre",
            countQuery = "select count(a) from Author a")
    Page<Book> findAllBooksWithPagination(Pageable pageable);

    @Query(value = "select b from Book b join fetch b.genre where b.id = :id")
    Optional<Book> findBookById(@Param("id")Long id);
}

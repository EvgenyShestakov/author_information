package ru.test.authorinformation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.test.authorinformation.domain.model.Author;

import java.util.List;

public interface AuthorService {
    Author saveAuthor(Author author);

    Author patchAuthor(Author author);

    Author putAuthor(Author author);

    void deleteAuthor(Long id);

    Page<Author> findAllAuthors(Pageable pageable);

    Author findAuthorById(Long id);

    List<Author> findAuthorByIds(List<Long> ids);
}

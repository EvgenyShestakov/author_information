package ru.test.authorinformation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.test.authorinformation.domain.model.Book;

public interface BookService {
    Book saveBook(Book book);

    Book patchBook(Book book);

    Book putBook(Book book);

    void deleteBook(Long id);

    Page<Book> findAllBooks(Pageable pageable);

    Book findBookById(Long id);
}

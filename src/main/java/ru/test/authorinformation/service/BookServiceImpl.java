package ru.test.authorinformation.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.authorinformation.domain.model.Book;
import ru.test.authorinformation.repository.BookRepository;
import ru.test.authorinformation.exeption.NotFoundInDataBaseException;

@Service
@Transactional
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book patchBook(Book book) {
        Book bookDb = bookRepository.findBookById(book.getId()).
                orElseThrow(() -> new NotFoundInDataBaseException("Book not found in database"));
        bookDb.setIsbn(book.getIsbn());
        return bookRepository.save(bookDb);
    }

    @Override
    public Book putBook(Book book) {
    Book dbBook = bookRepository.findBookById(book.getId()).
            orElseThrow(() -> new NotFoundInDataBaseException("Book not found in database"));
    book.setDateOfCreation(dbBook.getDateOfCreation());
    return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Page<Book> findAllBooks(Pageable pageable) {
        return bookRepository.findAllBooksWithPagination(pageable);
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findBookById(id).
                orElseThrow(() -> new NotFoundInDataBaseException("Book not found in database"));
    }
}

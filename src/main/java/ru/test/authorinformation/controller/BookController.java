package ru.test.authorinformation.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.test.authorinformation.domain.model.Book;
import ru.test.authorinformation.dto.BookDto;
import ru.test.authorinformation.dto.BookPatchDto;
import ru.test.authorinformation.service.ConverterService;
import ru.test.authorinformation.exeption.Operation;
import ru.test.authorinformation.service.BookService;

@Slf4j
@AllArgsConstructor
@RestController
public class BookController {
    private BookService bookService;
    private ConverterService converterService;

    @GetMapping("/books")
    public ResponseEntity<Page<Book>> findAllBooks(@PageableDefault(sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable) {
        return new ResponseEntity<>(bookService.findAllBooks(pageable), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Long id) {
        log.info(String.format("The input query parameter of the findBookById method -> %d", id));
        Book book = bookService.findBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> postBook(@Validated(Operation.
            OnCreate.class) @RequestBody BookDto bookDto) {
        log.info(String.format("The input query parameters of the postBook method -> %s",
                bookDto.toString()));
        Book book = converterService.toBookFromBookDto(bookDto);
        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/books")
    public ResponseEntity<Void> putBook(@Validated(Operation.
            OnUpdate.class) @RequestBody BookDto bookDto) {
        log.info(String.format("The input query parameters of the putBook method -> %s",
                bookDto.toString()));
        Book book = converterService.toBookFromBookDto(bookDto);
        bookService.putBook(book);
        return ResponseEntity.ok().build();
    }

    @PatchMapping ("/books")
    public ResponseEntity<Book> patchBook(@Validated(Operation.
            OnUpdate.class) @RequestBody BookPatchDto bookPatchDto) {
        log.info(String.format("The input query parameters of the patchBook method -> %s",
                bookPatchDto.toString()));
        Book book = converterService.toBookFromBookPatchDto(bookPatchDto);
        return new ResponseEntity<>(bookService.patchBook(book), HttpStatus.OK);
    }

    @DeleteMapping("books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        log.info(String.format("The input query parameter of the deleteBook method -> %d", id));
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}

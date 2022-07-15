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
import ru.test.authorinformation.domain.model.Author;
import ru.test.authorinformation.dto.AuthorDto;
import ru.test.authorinformation.dto.AuthorPatchDto;
import ru.test.authorinformation.service.ConverterService;
import ru.test.authorinformation.exeption.Operation;
import ru.test.authorinformation.service.AuthorService;

@Slf4j
@AllArgsConstructor
@RestController
public class AuthorController {
    private AuthorService authorService;
    private ConverterService converterService;

    @GetMapping("/authors")
    public ResponseEntity<Page<Author>> findAllAuthors(@PageableDefault(sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable) {
        return new ResponseEntity<>(authorService.findAllAuthors(pageable), HttpStatus.OK);
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> findAuthorById(@PathVariable Long id) {
        log.info(String.format("The input query parameter of the findAuthorById method -> %d", id));
        Author author = authorService.findAuthorById(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping("/authors")
    public ResponseEntity<Author> postAuthor(@Validated(Operation.
            OnCreate.class) @RequestBody AuthorDto authorDto) {
        log.info(String.format("The input query parameters of the postAuthor method -> %s",
                authorDto.toString()));
        Author author = converterService.toAuthorFromAuthorDto(authorDto);
        return new ResponseEntity<>(authorService.saveAuthor(author), HttpStatus.CREATED);
    }

    @PutMapping("/authors")
    public ResponseEntity<Void> putAuthor(@Validated(Operation.
            OnUpdate.class) @RequestBody AuthorDto authorDto) {
        log.info(String.format("The input query parameters of the putAuthor method -> %s",
                authorDto.toString()));
        Author author = converterService.toAuthorFromAuthorDto(authorDto);
        authorService.putAuthor(author);
        return ResponseEntity.ok().build();
    }

    @PatchMapping ("/authors")
    public ResponseEntity<Author> patchAuthor(@Validated(Operation.
            OnUpdate.class) @RequestBody AuthorPatchDto authorPatchDto) {
        log.info(String.format("The input query parameters of the patchAuthor method -> %s",
                authorPatchDto.toString()));
        Author author = converterService.toAuthorFromAuthorPatchDto(authorPatchDto);
        return new ResponseEntity<>(authorService.patchAuthor(author), HttpStatus.OK);
    }

    @DeleteMapping("authors/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        log.info(String.format("The input query parameter of the deleteAuthor method -> %d", id));
        authorService.deleteAuthor(id);
        return ResponseEntity.ok().build();
    }
}

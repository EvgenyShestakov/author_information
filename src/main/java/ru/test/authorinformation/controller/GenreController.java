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
import ru.test.authorinformation.domain.model.Genre;
import ru.test.authorinformation.dto.GenreDto;
import ru.test.authorinformation.exeption.Operation;
import ru.test.authorinformation.service.GenreService;

@Slf4j
@AllArgsConstructor
@RestController
public class GenreController {
    private GenreService genreService;

    @GetMapping("/genres")
    public ResponseEntity<Page<Genre>> findAll(@PageableDefault(sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable) {
        return new ResponseEntity<>(genreService.findAllGenres(pageable), HttpStatus.OK);
    }

    @GetMapping("/genres/{id}")
    public ResponseEntity<Genre> findGenreById(@PathVariable Long id) {
        log.info(String.format("The input query parameter of the findGenreById method -> %d", id));
        return new ResponseEntity<>(genreService.findGenreById(id), HttpStatus.OK);
    }

    @PostMapping("/genres")
    public ResponseEntity<Genre> postGenre(@Validated (Operation.
            OnCreate.class) @RequestBody GenreDto genre) {
        log.info(String.format("The input query parameters of the postGenre method -> %s",
                genre.toString()));
        return new ResponseEntity<>(genreService.
                saveGenre(new Genre(genre.getDescription())), HttpStatus.CREATED);
    }

    @PutMapping("/genres")
    public ResponseEntity<Void> putGenre(@Validated(Operation.
            OnUpdate.class) @RequestBody GenreDto genre) {
        log.info(String.format("The input query parameters of the putGenre method -> %s",
                genre.toString()));
        genreService.updateGenre(new Genre(genre.getId(), genre.getDescription()));
        return ResponseEntity.ok().build();
    }

    @PatchMapping ("/genres")
    public ResponseEntity<Genre> patchGenre(@Validated(Operation.
            OnUpdate.class) @RequestBody GenreDto genre) {
        log.info(String.format("The input query parameters of the patchGenre method -> %s",
                genre.toString()));
        return new ResponseEntity<>(genreService.
                updateGenre(new Genre(genre.getId(), genre.getDescription())), HttpStatus.OK);
    }

    @DeleteMapping("genres/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        log.info(String.format("The input query parameter of the deleteGenre method -> %d", id));
        genreService.deleteGenre(id);
        return ResponseEntity.ok().build();
    }
}

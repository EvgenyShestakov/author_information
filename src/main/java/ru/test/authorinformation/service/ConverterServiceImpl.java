package ru.test.authorinformation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.test.authorinformation.domain.model.Author;
import ru.test.authorinformation.domain.model.Book;
import ru.test.authorinformation.dto.*;
import ru.test.authorinformation.exeption.NotFoundInDataBaseException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ConverterServiceImpl implements ConverterService {
private AuthorService authorService;
private GenreService genreService;

    @Override
    public Author toAuthorFromAuthorDto(AuthorDto authorDto) {
        Author author = toAuthor(authorDto.getLastName(), authorDto.getFirstName(),
                authorDto.getPatronymic(), authorDto.getDateOfBirth());
        Long id = authorDto.getId();
        if (Objects.nonNull(id)) {
            author.setId(id);
        }
        author.setBooks(Arrays.stream(authorDto.getBooks()).
                map(bookDto -> new Book(bookDto.getIsbn(), genreService.
                        findGenreById(bookDto.getGenreId()))).
                collect(Collectors.toList()));
        return author;
    }

    @Override
    public Author toAuthorFromAuthorPatchDto(AuthorPatchDto authorPatchDto) {
        Author author = toAuthor(authorPatchDto.getLastName(), authorPatchDto.getFirstName(),
                authorPatchDto.getPatronymic(), authorPatchDto.getDateOfBirth());
        author.setId(authorPatchDto.getId());
        return author;
    }

    private Author toAuthor(String lastName, String firstName,
                                       String patronymic, LocalDate dateOfBirth) {
        Author author = new Author();
        author.setLastName(lastName);
        author.setFirstName(firstName);
        author.setPatronymic(patronymic);
        author.setDateOfBirth(dateOfBirth);
        return author;
    }

    public Book toBookFromBookDto(BookDto bookDto) {
        List<Author> authors = authorService.findAuthorByIds(Arrays.
                stream(bookDto.getAuthorIds()).map(NestedAuthorDto::getId).
                collect(Collectors.toList()));
        if (authors.size() == 0) {
        throw new NotFoundInDataBaseException("Authors not found");
        }
        Book book = new Book();
        book.setGenre(genreService.findGenreById(bookDto.getGenreId()));
        Long id = bookDto.getId();
        if (Objects.nonNull(id)) {
            book.setId(id);
        }
        book.setIsbn(bookDto.getIsbn());
        book.setAuthors(authors);
        return book;
    }

    public Book toBookFromBookPatchDto(BookPatchDto bookPatchDto) {
        Book book = new Book();
        book.setId(bookPatchDto.getId());
        book.setIsbn(bookPatchDto.getIsbn());
        return book;
    }
}

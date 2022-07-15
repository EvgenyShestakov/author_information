package ru.test.authorinformation.service;

import ru.test.authorinformation.domain.model.Author;
import ru.test.authorinformation.domain.model.Book;
import ru.test.authorinformation.dto.AuthorDto;
import ru.test.authorinformation.dto.AuthorPatchDto;
import ru.test.authorinformation.dto.BookDto;
import ru.test.authorinformation.dto.BookPatchDto;

public interface ConverterService {
    Author toAuthorFromAuthorDto(AuthorDto authorDto);

    Author toAuthorFromAuthorPatchDto(AuthorPatchDto authorPatchDto);

    Book toBookFromBookDto(BookDto bookDto);

    Book toBookFromBookPatchDto(BookPatchDto bookPatchDto);
}
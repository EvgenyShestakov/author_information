package ru.test.authorinformation.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.authorinformation.domain.model.Author;
import ru.test.authorinformation.exeption.NotFoundInDataBaseException;
import ru.test.authorinformation.repository.AuthorRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author patchAuthor(Author author) {
        Author authorDb = authorRepository.findAuthorById(author.getId()).
                orElseThrow(() -> new NotFoundInDataBaseException("Author not found in database"));
        authorDb.setLastName(author.getLastName());
        authorDb.setFirstName(author.getFirstName());
        authorDb.setPatronymic(author.getPatronymic());
        authorDb.setDateOfBirth(author.getDateOfBirth());
        return authorRepository.save(authorDb);
    }

    @Override
    public Author putAuthor(Author author) {
        Author authorDb = authorRepository.findById(author.getId()).
                orElseThrow(() -> new NotFoundInDataBaseException("Author not found in database"));
        author.setDateOfCreation(authorDb.getDateOfCreation());
       return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
    authorRepository.deleteById(id);
    }

    @Override
    public Page<Author> findAllAuthors(Pageable pageable) {
        return authorRepository.findAllAuthorsWithPagination(pageable);
    }

    @Override
    public Author findAuthorById(Long id) {
        return authorRepository.findAuthorById(id).
                orElseThrow(() -> new NotFoundInDataBaseException("Author not found in database"));
    }

    @Override
    public List<Author> findAuthorByIds(List<Long> ids) {
        return authorRepository.findAllById(ids);
    }
}

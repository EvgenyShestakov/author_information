package ru.test.authorinformation.exeption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ValidationControllerAdvise {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<Map<String, String>> handle(MethodArgumentNotValidException e) {
        List<Map<String, String>> messages = e.getFieldErrors().stream()
                 .map(f -> Map.of(f.getField(), String.
                         format("%s. Actual value: %s",
                                 f.getDefaultMessage(), f.getRejectedValue())
                 )).collect(Collectors.toList());
        log.error(e.getMessage(), e);
        return messages;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundInDataBaseException.class)
    public String handle(NotFoundInDataBaseException e) {
        log.error(e.getMessage(), e);
        return e.getMessage();
    }
}

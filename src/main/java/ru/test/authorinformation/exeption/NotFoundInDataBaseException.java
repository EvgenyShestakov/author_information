package ru.test.authorinformation.exeption;

public class NotFoundInDataBaseException extends RuntimeException {
    public NotFoundInDataBaseException(String message) {
        super(message);
    }
}

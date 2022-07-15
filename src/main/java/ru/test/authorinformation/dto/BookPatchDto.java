package ru.test.authorinformation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.test.authorinformation.exeption.Operation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class BookPatchDto {
    @NotNull(message = "Book id must not be null", groups = Operation.OnUpdate.class)
    private Long id;

    @NotNull(message = "ISBN id must not be null",
            groups = {Operation.OnCreate.class, Operation.OnUpdate.class})
    @Pattern(regexp = "ISBN(?:-13)?:?\\x20*(?=.{17}$)97(?:8|9)"
            + "([ -])\\d{1,5}\\1\\d{1,7}\\1\\d{1,6}\\1\\d$",
            groups = {Operation.OnCreate.class, Operation.OnUpdate.class})
    private String isbn;
}

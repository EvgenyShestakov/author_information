package ru.test.authorinformation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.test.authorinformation.exeption.Operation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class NestedBookDto {
    @NotNull(message = "ISBN id must not be null",
            groups = {Operation.OnCreate.class, Operation.OnUpdate.class})
    @Pattern(regexp = "ISBN(?:-13)?:?\\x20*(?=.{17}$)97(?:8|9)"
            + "([ -])\\d{1,5}\\1\\d{1,7}\\1\\d{1,6}\\1\\d$",
            message = "ISBN does not match the format",
            groups = {Operation.OnCreate.class, Operation.OnUpdate.class})
    private String isbn;

    @Min(value = 1, message = "Genre id cannot be less than 1",
            groups = {Operation.OnCreate.class, Operation.OnUpdate.class})
    @NotNull(message = "Genre id must not be null",
            groups = {Operation.OnCreate.class, Operation.OnUpdate.class})
    private Long genreId;
}

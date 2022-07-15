package ru.test.authorinformation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.test.authorinformation.exeption.Operation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class GenreDto {
    @Min(value = 1, message = "Genre id cannot be less than 1", groups = Operation.OnUpdate.class)
    @NotNull(message = "Genre id must not be null",
            groups = Operation.OnUpdate.class)
    private Long id;

    @NotBlank(message = "Description field is empty",
            groups = {Operation.OnCreate.class, Operation.OnUpdate.class})
    private String description;
}

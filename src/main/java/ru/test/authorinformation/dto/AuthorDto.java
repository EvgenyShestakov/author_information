package ru.test.authorinformation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import ru.test.authorinformation.exeption.Operation;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AuthorDto {
    @Min(value = 1, message = "Author id cannot be less than 1", groups = Operation.OnUpdate.class)
    @NotNull(message = "Author id must not be null", groups = Operation.OnUpdate.class)
    private Long id;

    @NotBlank(message = "Lastname field is empty",
            groups = {Operation.OnCreate.class, Operation.OnUpdate.class})
    private String lastName;

    @NotBlank(message = "Firstname field is empty",
            groups = {Operation.OnCreate.class, Operation.OnUpdate.class})
    private String firstName;

    @NotBlank(message = "Patronymic field is empty",
            groups = {Operation.OnCreate.class, Operation.OnUpdate.class})
    private String patronymic;

    @NotNull(message = "DateOfBirth must not be null",
            groups = {Operation.OnCreate.class, Operation.OnUpdate.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotNull(message = "Books field is empty",
            groups = {Operation.OnCreate.class, Operation.OnUpdate.class})
    @Valid
    private NestedBookDto[] books;
}

package ru.test.authorinformation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.test.authorinformation.exeption.Operation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class NestedAuthorDto {
    @Min(value = 1, message = "Author id cannot be less than 1",
            groups = {Operation.OnCreate.class, Operation.OnUpdate.class})
    @NotNull(groups = Operation.OnUpdate.class, message = "Author id must not be null")
    private Long id;
}

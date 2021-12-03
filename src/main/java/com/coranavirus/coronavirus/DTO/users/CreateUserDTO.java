package com.coranavirus.coronavirus.DTO.users;

import com.coranavirus.coronavirus.validator.ExistEmail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserDTO {
    @NotNull
    @NotEmpty(message = "email field required")
    @Email
    @ExistEmail
    private String email;
}

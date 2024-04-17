package org.happybaras.taller2.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SaveUserDTO {
    @NotBlank
    @Email
    String email;

    @NotBlank
    @Pattern(regexp = "^[a-z]{4,16}$")
    String username;

    @NotBlank
    @Pattern(regexp = "^(?=.[a-z])(?=.[A-Z])(?=.*[!#$])[a-zA-Z!#$]{8,32}$")
    String password;
}

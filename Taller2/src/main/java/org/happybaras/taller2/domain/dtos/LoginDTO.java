package org.happybaras.taller2.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank()
    String identifier;

    @NotBlank()
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!#$])[a-zA-Z!#$]{8,32}$")
    String password;
}

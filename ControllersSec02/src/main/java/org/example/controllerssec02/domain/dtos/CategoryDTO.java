package org.example.controllerssec02.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDTO {
    @NotBlank
    private String code;

    @NotBlank
    private String name;
}

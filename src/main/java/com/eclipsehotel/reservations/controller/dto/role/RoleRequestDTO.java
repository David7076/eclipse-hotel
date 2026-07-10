package com.eclipsehotel.reservations.controller.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RoleRequestDTO(
        @Size(max = 50)
        @NotBlank(message = "Nome do role é obrigatório! ")
        String name
) {
}

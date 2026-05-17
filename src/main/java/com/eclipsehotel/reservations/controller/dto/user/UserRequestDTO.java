package com.eclipsehotel.reservations.controller.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @Size(max = 50)
        @NotBlank(message = "Nome é obrigatório!")
        String name,

        @Size(max = 50)
        @Email(message = "E-mail inválido!")
        String email,


        @NotBlank(message = "Senha é obrigatória!")
        String password
) {
}

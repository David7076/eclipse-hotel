package com.eclipsehotel.reservations.controller.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(

        @Email(message = "Formato de e-mail inválido! ")
        @NotBlank(message = "E-mail é obrigatório! ")
        String email,

        @NotBlank(message = "Senha é obrigatória! ")
        String password
) {
}

package com.eclipsehotel.reservations.controller.dto.user;

import com.eclipsehotel.reservations.domain.models.enums.Role;
import jakarta.validation.constraints.*;

public record UserRequestDTO(

        @Size(max = 50)
        @Email(message = "E-mail inválido!")
        String email,

        @NotBlank(message = "Senha é obrigatória!")
        String password,

        @NotBlank(message = "O perfil (role) é obrigatório")
        @Pattern(
                regexp = "^(ADMIN|USER|RECEPTIONIST)$",
                message = "Perfil inválido. Valores aceitos: ADMIN, USER ou RECEPTIONIST"
        )
        Role role
) {
}

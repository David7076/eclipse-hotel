package com.eclipsehotel.reservations.controller.dto.user;


import com.eclipsehotel.reservations.domain.models.enums.Role;
import jakarta.validation.constraints.Pattern;

public record UserUpdateRequestDTO(
        String email,
        String password,
        @Pattern(
                regexp = "^(ADMIN|USER|RECEPTIONIST)$",
                message = "Perfil inválido. Valores aceitos: ADMIN, USER ou RECEPTIONIST"
        )
        Role role
) {
}

package com.eclipsehotel.reservations.controller.dto.user;

import com.eclipsehotel.reservations.domain.models.enums.Role;
import lombok.Builder;

@Builder
public record UserResponseDTO(
        Long id, String email, Role role
) {
}

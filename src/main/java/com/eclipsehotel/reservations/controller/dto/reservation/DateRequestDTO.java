package com.eclipsehotel.reservations.controller.dto.reservation;

import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;

public record DateRequestDTO(
        @NotNull
        LocalDate startDate,

        @NotNull
        LocalDate endDate
) {
}

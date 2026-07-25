package com.eclipsehotel.reservations.controller.dto.room;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoomsRequestDTO(
        @NotBlank(message = "Necessário informar o número do quarto")
        String roomNumber,

        @NotBlank(message = "Necessário informar o tipo do quarto")
        String type,

        @NotNull(message = "Necessário informar o preço da diária")
        Double price
) {
}

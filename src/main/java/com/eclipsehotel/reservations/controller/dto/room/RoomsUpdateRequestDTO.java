package com.eclipsehotel.reservations.controller.dto.room;

public record RoomsUpdateRequestDTO(
        String roomNumber,
        String type,
        Double price
) {
}

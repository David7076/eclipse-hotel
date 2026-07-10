package com.eclipsehotel.reservations.controller.dto.rooms;

public record RoomsUpdateRequestDTO(
        String roomNumber,
        String type,
        Double price
) {
}

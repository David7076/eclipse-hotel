package com.eclipsehotel.reservations.controller.dto.room;

public record RoomDetailResponseDTO(
        String roomNumber,
        String type,
        Double price
) {
}

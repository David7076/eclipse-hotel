package com.eclipsehotel.reservations.controller.dto.rooms;

public record RoomDetailResponseDTO(
        String roomNumber,
        String type,
        Double price
) {
}

package com.eclipsehotel.reservations.controller.dto.room;

import com.eclipsehotel.reservations.domain.models.RoomsEntity;

public record RoomsResponseDTO(
        Long id,
        String roomNumber,
        String type,
        Double price
) {
    public RoomsResponseDTO(RoomsEntity entity) {
        this(entity.getId(), entity.getRoomNumber(), entity.getType(), entity.getPrice());
    }
}

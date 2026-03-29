package com.eclipsehotel.reservations.domain.mapper;

import com.eclipsehotel.reservations.controller.dto.rooms.RoomsRequestDTO;
import com.eclipsehotel.reservations.controller.dto.rooms.RoomsUpdateRequestDTO;
import com.eclipsehotel.reservations.controller.dto.rooms.RoomsResponseDTO;
import com.eclipsehotel.reservations.domain.models.RoomsEntity;

public class RoomsMapper {
    public static RoomsEntity toEntity(RoomsRequestDTO dto) {
        RoomsEntity entity = new RoomsEntity();
        entity.setRoomNumber(dto.roomNumber());
        entity.setType(dto.type());
        entity.setPrice(dto.price());
        return entity;
    }

    public static RoomsResponseDTO toDTO(RoomsEntity entity) {
        if(entity == null) return null;
        return new RoomsResponseDTO(
                entity.getId(),
                entity.getRoomNumber(),
                entity.getType(),
                entity.getPrice()
        );
    }

    public static RoomsEntity toEntity(RoomsUpdateRequestDTO dto) {
        if(dto == null) return null;
        RoomsEntity entity = new RoomsEntity();
        entity.setRoomNumber(dto.roomNumber());
        entity.setType(dto.type());
        entity.setPrice(dto.price());
        return entity;
    }
}

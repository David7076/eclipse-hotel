package com.eclipsehotel.reservations.domain.services;

import com.eclipsehotel.reservations.controller.dto.room.RoomsRequestDTO;
import com.eclipsehotel.reservations.controller.dto.room.RoomsUpdateRequestDTO;
import com.eclipsehotel.reservations.domain.models.RoomsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRoomService {
    RoomsEntity saveRoom(RoomsRequestDTO dto);

    Page<RoomsEntity> listAllRooms(Pageable pageable);

    RoomsEntity getByIdRoom(Long id);

    RoomsEntity update(RoomsUpdateRequestDTO dto, Long id);

    void delete(Long id);
}

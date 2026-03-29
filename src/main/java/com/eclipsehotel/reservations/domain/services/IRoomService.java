package com.eclipsehotel.reservations.domain.services;

import com.eclipsehotel.reservations.controller.dto.rooms.RoomDetailResponseDTO;
import com.eclipsehotel.reservations.controller.dto.rooms.RoomsRequestDTO;
import com.eclipsehotel.reservations.controller.dto.rooms.RoomsResponseDTO;
import com.eclipsehotel.reservations.controller.dto.rooms.RoomsUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRoomService {
    RoomsResponseDTO saveRoom(RoomsRequestDTO dto);

    Page<RoomsResponseDTO> listAllRooms(Pageable pageable);

    RoomDetailResponseDTO getByIdRoom(Long id);

    RoomsResponseDTO update(RoomsUpdateRequestDTO dto, Long id);

    void delete(Long id);
}

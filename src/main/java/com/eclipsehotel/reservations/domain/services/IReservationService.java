package com.eclipsehotel.reservations.domain.services;

import com.eclipsehotel.reservations.controller.dto.reservation.DateRequestDTO;
import com.eclipsehotel.reservations.controller.dto.reservation.ReservationRequestDTO;
import com.eclipsehotel.reservations.controller.dto.reservation.ReservationResponseDTO;
import com.eclipsehotel.reservations.controller.dto.rooms.RoomDetailResponseDTO;

import java.util.List;

public interface IReservationService {
    ReservationResponseDTO saveReservation(ReservationRequestDTO dto);

    ReservationResponseDTO checkout(Long id);

    List<ReservationResponseDTO> getReservationByDate(DateRequestDTO dto);

    List<RoomDetailResponseDTO> findCurrentlyOccupiedRooms();
}

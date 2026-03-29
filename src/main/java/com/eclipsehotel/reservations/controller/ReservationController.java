package com.eclipsehotel.reservations.controller;

import com.eclipsehotel.reservations.controller.dto.reservation.DateRequestDTO;
import com.eclipsehotel.reservations.controller.dto.reservation.ReservationRequestDTO;
import com.eclipsehotel.reservations.controller.dto.reservation.ReservationResponseDTO;
import com.eclipsehotel.reservations.controller.dto.rooms.RoomDetailResponseDTO;
import com.eclipsehotel.reservations.domain.services.impl.ReservationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    public final ReservationServiceImpl service;

    public ReservationController(ReservationServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/createReservation")
    @Transactional
    public ResponseEntity<ReservationResponseDTO> createReservation(
            @RequestBody @Valid ReservationRequestDTO dto,
            UriComponentsBuilder uriBuilder) {
        var response = service.saveReservation(dto);
        var uri = uriBuilder.path("/reservation/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("{id}/checkout")
    @Transactional
    public ResponseEntity<ReservationResponseDTO> checkout (@PathVariable Long id) {
        return ResponseEntity.ok(service.checkout(id));
    }

    @GetMapping("/reservationBydata")
    public ResponseEntity<List<ReservationResponseDTO>> getReservationByDate(@RequestBody DateRequestDTO dto) {
        return ResponseEntity.ok(service.getReservationByDate(dto));
    }

    @GetMapping("/rooms/occupied")
    public ResponseEntity<List<RoomDetailResponseDTO>> getCurrentlyOccupiedRooms() {
        var rooms = service.findCurrentlyOccupiedRooms();
        return ResponseEntity.ok(rooms);
    }

}

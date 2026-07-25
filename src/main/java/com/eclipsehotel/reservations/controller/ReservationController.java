package com.eclipsehotel.reservations.controller;

import java.util.List;

import com.eclipsehotel.reservations.domain.services.IReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.eclipsehotel.reservations.controller.dto.reservation.DateRequestDTO;
import com.eclipsehotel.reservations.controller.dto.reservation.ReservationRequestDTO;
import com.eclipsehotel.reservations.controller.dto.reservation.ReservationResponseDTO;
import com.eclipsehotel.reservations.controller.dto.room.RoomDetailResponseDTO;
import com.eclipsehotel.reservations.domain.services.impl.ReservationServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    public final IReservationService service;

    public ReservationController(ReservationServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/createReservation")
    public ResponseEntity<ReservationResponseDTO> createReservation(
            @RequestBody @Valid ReservationRequestDTO dto,
            UriComponentsBuilder uriBuilder) {
        var response = service.saveReservation(dto);
        var uri = uriBuilder.path("/reservation/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("{id}/checkout")
    public ResponseEntity<ReservationResponseDTO> checkout(@PathVariable Long id) {
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

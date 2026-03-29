package com.eclipsehotel.reservations.controller;


import com.eclipsehotel.reservations.controller.dto.rooms.RoomsRequestDTO;
import com.eclipsehotel.reservations.controller.dto.rooms.RoomsUpdateRequestDTO;
import com.eclipsehotel.reservations.controller.dto.rooms.RoomDetailResponseDTO;
import com.eclipsehotel.reservations.controller.dto.rooms.RoomsResponseDTO;
import com.eclipsehotel.reservations.domain.services.impl.RoomsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/rooms")
public class RoomsController {

    private final RoomsServiceImpl service;

    public RoomsController(RoomsServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<RoomsResponseDTO> createCustomers(
            @RequestBody @Valid RoomsRequestDTO dto,
            UriComponentsBuilder uriBuilder)
    {
        var response = service.saveRoom(dto);
        var uri = uriBuilder.path("/rooms/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<RoomsResponseDTO>> getAllRooms(@PageableDefault Pageable pagination) {
        var page = service.listAllRooms(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/getRoom/{id}")
    public ResponseEntity<RoomDetailResponseDTO> getByIdRoom(@PathVariable Long id) {
        return ResponseEntity.ok(service.getByIdRoom(id));
    }

    @PutMapping("/updateRoom/{id}")
    @Transactional
    public ResponseEntity<RoomsResponseDTO> updateRoom(
            @RequestBody
            @Valid RoomsUpdateRequestDTO dto,
            @PathVariable Long id) {
        return ResponseEntity.ok(service.update(dto, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

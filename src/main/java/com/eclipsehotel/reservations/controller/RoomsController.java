package com.eclipsehotel.reservations.controller;


import com.eclipsehotel.reservations.controller.dto.room.RoomsRequestDTO;
import com.eclipsehotel.reservations.controller.dto.room.RoomsUpdateRequestDTO;
import com.eclipsehotel.reservations.controller.dto.room.RoomsResponseDTO;
import com.eclipsehotel.reservations.domain.mapper.RoomsMapper;
import com.eclipsehotel.reservations.domain.services.IRoomService;
import com.eclipsehotel.reservations.domain.services.impl.RoomsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/rooms")
public class RoomsController {

    private final IRoomService service;

    public RoomsController(RoomsServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<RoomsResponseDTO> createCustomers(
            @RequestBody @Valid RoomsRequestDTO dto,
            UriComponentsBuilder uriBuilder)
    {
        var response = service.saveRoom(dto);
        var uri = uriBuilder.path("/rooms/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(RoomsMapper.toDTO(response));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<RoomsResponseDTO>> getAllRooms(@PageableDefault Pageable pagination) {
        var page = service.listAllRooms(pagination);
        return ResponseEntity.ok(page.map(RoomsResponseDTO::new));
    }

    @GetMapping("/getRoom/{id}")
    public ResponseEntity<RoomsResponseDTO> getByIdRoom(@PathVariable Long id) {
        var room = service.getByIdRoom(id);
        return ResponseEntity.ok(RoomsMapper.toDTO(room));
    }

    @PutMapping("/updateRoom/{id}")
    public ResponseEntity<RoomsResponseDTO> updateRoom(
            @RequestBody
            @Valid RoomsUpdateRequestDTO dto,
            @PathVariable Long id) {
        var roomUpdated = service.update(dto, id);
        return ResponseEntity.ok(RoomsMapper.toDTO(roomUpdated));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

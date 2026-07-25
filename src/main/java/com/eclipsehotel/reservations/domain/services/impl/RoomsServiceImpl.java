package com.eclipsehotel.reservations.domain.services.impl;

import com.eclipsehotel.reservations.controller.dto.room.RoomsRequestDTO;
import com.eclipsehotel.reservations.controller.dto.room.RoomsUpdateRequestDTO;
import com.eclipsehotel.reservations.controller.dto.room.RoomDetailResponseDTO;
import com.eclipsehotel.reservations.controller.dto.room.RoomsResponseDTO;
import com.eclipsehotel.reservations.domain.models.RoomsEntity;
import com.eclipsehotel.reservations.domain.mapper.RoomsMapper;
import com.eclipsehotel.reservations.domain.services.IRoomService;
import com.eclipsehotel.reservations.infra.repository.RoomsRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RoomsServiceImpl implements IRoomService {
    private final RoomsRepository repository;

    public RoomsServiceImpl(RoomsRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public RoomsResponseDTO saveRoom(RoomsRequestDTO dto) {
        RoomsEntity entity = RoomsMapper.toEntity(dto);
        repository.save(entity);
        return RoomsMapper.toDTO(entity);
    }

    @Override
    public Page<RoomsResponseDTO> listAllRooms(Pageable pageable) {
        return repository.findAll(pageable).map(RoomsResponseDTO::new);
    }

    @Override
    public RoomDetailResponseDTO getByIdRoom(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Quarto não encontrado"));
        return new RoomDetailResponseDTO(
                entity.getRoomNumber(),
                entity.getType(),
                entity.getPrice()
        );
    }

    @Override
    @Transactional
    public RoomsResponseDTO update(RoomsUpdateRequestDTO dto, Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Quarto não encontrado! "));

        if (dto.roomNumber() != null) entity.setRoomNumber(dto.roomNumber());
        if (dto.type() != null) entity.setType(dto.type());
        if (dto.price() != null) entity.setPrice(dto.price());

        repository.save(entity);
        return RoomsMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

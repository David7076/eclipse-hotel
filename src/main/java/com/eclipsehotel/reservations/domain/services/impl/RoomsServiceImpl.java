package com.eclipsehotel.reservations.domain.services.impl;

import com.eclipsehotel.reservations.controller.dto.room.RoomsRequestDTO;
import com.eclipsehotel.reservations.controller.dto.room.RoomsUpdateRequestDTO;
import com.eclipsehotel.reservations.domain.models.RoomsEntity;
import com.eclipsehotel.reservations.domain.mapper.RoomsMapper;
import com.eclipsehotel.reservations.domain.services.IRoomService;
import com.eclipsehotel.reservations.infra.repository.RoomsRepository;
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
    public RoomsEntity saveRoom(RoomsRequestDTO dto) {
        RoomsEntity entity = RoomsMapper.toEntity(dto);
        return repository.save(entity);
    }

    @Override
    public Page<RoomsEntity> listAllRooms(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public RoomsEntity getByIdRoom(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Quarto não encontrado"));
    }

    @Override
    @Transactional
    public RoomsEntity update(RoomsUpdateRequestDTO dto, Long id) {
        var entity = getByIdRoom(id);
        if (dto.roomNumber() != null) entity.setRoomNumber(dto.roomNumber());
        if (dto.type() != null) entity.setType(dto.type());
        if (dto.price() != null) entity.setPrice(dto.price());

        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

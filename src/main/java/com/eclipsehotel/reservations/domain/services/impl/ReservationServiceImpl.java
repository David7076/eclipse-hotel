package com.eclipsehotel.reservations.domain.services.impl;


import com.eclipsehotel.reservations.controller.dto.reservation.DateRequestDTO;
import com.eclipsehotel.reservations.controller.dto.reservation.ReservationRequestDTO;
import com.eclipsehotel.reservations.controller.dto.reservation.ReservationResponseDTO;
import com.eclipsehotel.reservations.controller.dto.rooms.RoomDetailResponseDTO;
import com.eclipsehotel.reservations.domain.models.ReservationsEntity;
import com.eclipsehotel.reservations.domain.models.enums.ReservationStatus;
import com.eclipsehotel.reservations.domain.mapper.ReservationMapper;
import com.eclipsehotel.reservations.domain.services.IReservationService;
import com.eclipsehotel.reservations.infra.repository.CustomersRepository;
import com.eclipsehotel.reservations.infra.repository.ReservationRepository;
import com.eclipsehotel.reservations.infra.repository.RoomsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository repository;
    private final CustomersRepository customersRepository;
    private final RoomsRepository roomsRepository;

    public ReservationServiceImpl(ReservationRepository repository, CustomersRepository customersRepository, RoomsRepository roomsRepository) {
        this.repository = repository;
        this.customersRepository = customersRepository;
        this.roomsRepository = roomsRepository;
    }

    public ReservationResponseDTO saveReservation(ReservationRequestDTO dto) {
        if (dto.checkin().isAfter(dto.checkout()) || dto.checkin().isEqual(dto.checkout()))
            throw new IllegalArgumentException("A data de check-in deve ser anterior à data de check-out.");

        var customer = customersRepository.findById(dto.customers().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado! "));

        var room = roomsRepository.findById(dto.rooms().getId())
                .orElseThrow(() -> new EntityNotFoundException("Quarto não encontrado! "));

        var conflicts = repository.findConflictingReservations(
                dto.customers().getId(),
                dto.checkin(),
                dto.checkout()
        );

        if (!conflicts.isEmpty()) {
            throw new IllegalArgumentException("O quarto já está reservado para as datas selecionadas");
        }

        ReservationsEntity entity = new ReservationsEntity();
        entity.setCustomer(customer);
        entity.setRoom(room);
        entity.setCheckin(dto.checkin());
        entity.setCheckout(dto.checkout());
        entity.setStatus(ReservationStatus.SCHEDULED);
        repository.save(entity);
        log.info("Reserva criada...");

        return ReservationMapper.toDTO(entity);

    }

    public ReservationResponseDTO checkout(Long id) {
        var reservation = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada"));

        if (reservation.getStatus().equals(ReservationStatus.IN_USE)) {
            reservation.setStatus(ReservationStatus.FINISHED);
            repository.save(reservation);
        } else
            throw new IllegalArgumentException("Não é possível finalizar uma reserva que não esteja em uso");

        return ReservationMapper.toDTO(reservation);
    }

    public List<ReservationResponseDTO> getReservationByDate(DateRequestDTO dto) {
        if (dto.startDate() == null || dto.endDate() == null) {
            throw new IllegalArgumentException("a data de início e a data de término são obrigatórias");
        }


        if (!dto.startDate().isBefore(dto.endDate())) {
            throw new IllegalArgumentException("\n" +
                    "A data de início deve ser anterior à data de término.");
        }

        var reservations = repository.findReservationsByDateRange(dto.startDate(), dto.endDate());

        return reservations.stream()
                .map(ReservationResponseDTO::new)
                .toList();
    }

    public List<RoomDetailResponseDTO> findCurrentlyOccupiedRooms() {
        var inUseReservations = repository.findByStatus(ReservationStatus.IN_USE);

        return inUseReservations.stream()
                .map(ReservationsEntity::getRoom)
                .distinct()
                .map(room -> new RoomDetailResponseDTO(
                        room.getRoomNumber(),
                        room.getType(),
                        room.getPrice()
                ))
                .toList();
    }
}

package com.eclipsehotel.reservations.domain.mapper;


import com.eclipsehotel.reservations.controller.dto.reservation.ReservationResponseDTO;
import com.eclipsehotel.reservations.domain.models.ReservationsEntity;

public class ReservationMapper {
    public static ReservationResponseDTO toDTO(ReservationsEntity entity) {
        if(entity == null) return null;
        return new ReservationResponseDTO(
                entity.getId(),
                entity.getCustomer(),
                entity.getRoom(),
                entity.getCheckin(),
                entity.getCheckout(),
                entity.getStatus()
        );
    }
}

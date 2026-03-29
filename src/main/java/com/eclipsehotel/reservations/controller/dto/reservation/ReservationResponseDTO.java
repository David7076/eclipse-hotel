package com.eclipsehotel.reservations.controller.dto.reservation;

import com.eclipsehotel.reservations.domain.models.CustomersEntity;
import com.eclipsehotel.reservations.domain.models.ReservationsEntity;
import com.eclipsehotel.reservations.domain.models.RoomsEntity;
import com.eclipsehotel.reservations.domain.models.enums.ReservationStatus;

import java.time.LocalDate;

public record ReservationResponseDTO(
        Long id,
        CustomersEntity customers,
        RoomsEntity rooms,
        LocalDate checkin,
        LocalDate checkout,
        ReservationStatus status
) {
    public ReservationResponseDTO(ReservationsEntity entity) {
        this(entity.getId(), entity.getCustomer(), entity.getRoom(), entity.getCheckin(), entity.getCheckout(), entity.getStatus());
    }
}

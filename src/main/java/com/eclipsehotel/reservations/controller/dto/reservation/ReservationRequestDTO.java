package com.eclipsehotel.reservations.controller.dto.reservation;

import com.eclipsehotel.reservations.domain.models.CustomersEntity;
import com.eclipsehotel.reservations.domain.models.RoomsEntity;

import java.time.LocalDate;

public record ReservationRequestDTO(
       CustomersEntity customers,
       RoomsEntity rooms,
       LocalDate checkin,
       LocalDate checkout
) {
}

package com.eclipsehotel.reservations.controller.dto.customer;

public record CustomerUpdateRequestDTO(
        String name,
        String email,
        String phone,
        String cep,
        String number,
        String addressDetails

) {
}

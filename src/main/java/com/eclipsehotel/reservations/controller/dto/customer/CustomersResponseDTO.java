package com.eclipsehotel.reservations.controller.dto.customer;

import com.eclipsehotel.reservations.domain.models.AddressEntity;
import com.eclipsehotel.reservations.domain.models.CustomersEntity;

public record CustomersResponseDTO(
        Long id,
        String name,
        String email,
        String telefone,
        AddressEntity address
) {
    public CustomersResponseDTO(CustomersEntity entity) {
        this(entity.getId(), entity.getName(), entity.getEmail(), entity.getPhone(), entity.getAddress());
    }
}

package com.eclipsehotel.reservations.domain.mapper;

import com.eclipsehotel.reservations.controller.dto.external.ViaCepResponseDTO;
import com.eclipsehotel.reservations.domain.models.AddressEntity;

public class AddressMapper {
    public static AddressEntity toEntity(ViaCepResponseDTO dto) {
        AddressEntity entity = new AddressEntity();
        entity.setZipCode(dto.zipCode());
        entity.setStreet(dto.street());
        entity.setNeighborhood(dto.neighborhood());
        entity.setState(dto.state());
        entity.setCity(dto.city());
        return entity;
    }
}

package com.eclipsehotel.reservations.domain.mapper;

import com.eclipsehotel.reservations.controller.dto.external.CepResponseDTO;
import com.eclipsehotel.reservations.domain.models.AddressEntity;

public class AddressMapper {
    public static AddressEntity toEntity(CepResponseDTO dto) {
        AddressEntity entity = new AddressEntity();
        entity.setCep(dto.cep());
        entity.setStreet(dto.street());
        entity.setNeighborhood(dto.neighborhood());
        entity.setState(dto.state());
        entity.setCity(dto.city());
        return entity;
    }
}

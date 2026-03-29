package com.eclipsehotel.reservations.domain.mapper;

import com.eclipsehotel.reservations.controller.dto.customer.CustomersRequestDTO;
import com.eclipsehotel.reservations.controller.dto.customer.CustomerUpdateRequestDTO;
import com.eclipsehotel.reservations.controller.dto.customer.CustomersResponseDTO;
import com.eclipsehotel.reservations.domain.models.CustomersEntity;

public class CustomersMapper {
    public static CustomersEntity toEntity(CustomersRequestDTO dto) {
        CustomersEntity entity = new CustomersEntity();
        entity.setName(dto.name());
        entity.setEmail(dto.email());
        entity.setPhone(dto.phone());
        entity.setCreatedAt(dto.create_at());
        return entity;
    }

    public static CustomersResponseDTO toDTO(CustomersEntity entity) {
        if(entity == null) return null;
        return new CustomersResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress()
        );
    }

    public static CustomersEntity toEntity(CustomerUpdateRequestDTO dto) {
        if(dto == null) return null;
        CustomersEntity entity = new CustomersEntity();
        entity.setName(dto.name());
        entity.setEmail(dto.email());
        entity.setPhone(dto.phone());
        return entity;
    }

}

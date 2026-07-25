package com.eclipsehotel.reservations.domain.mapper;


import com.eclipsehotel.reservations.controller.dto.user.UserRequestDTO;
import com.eclipsehotel.reservations.controller.dto.user.UserResponseDTO;
import com.eclipsehotel.reservations.controller.dto.user.UserUpdateRequestDTO;
import com.eclipsehotel.reservations.domain.models.UserEntity;


public class UserMapper {

    public static UserEntity toEntity(UserRequestDTO dto) {
        return UserEntity
                .builder()
                .email(dto.email())
                .password(dto.password())
                .role(dto.role())
                .build();
    }

    public static UserResponseDTO toDTO(UserEntity entity) {
        if(entity == null) return null;
        return UserResponseDTO.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .role(entity.getRole())
                .build();
    }

    public static UserEntity toEntity(UserUpdateRequestDTO dto) {
        if(dto == null) return null;
        UserEntity entity = new UserEntity();
        entity.setEmail(dto.email());
        entity.setPassword(dto.password());
        entity.setRole(dto.role());
        return entity;
    }

}

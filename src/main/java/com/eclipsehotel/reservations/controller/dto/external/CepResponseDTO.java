package com.eclipsehotel.reservations.controller.dto.external;


public record CepResponseDTO(
        String cep,
        String state,
        String city,
        String neighborhood,
        String street,
        String service
) {

}

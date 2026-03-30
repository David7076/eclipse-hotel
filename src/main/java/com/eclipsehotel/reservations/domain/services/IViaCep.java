package com.eclipsehotel.reservations.domain.services;

import com.eclipsehotel.reservations.controller.dto.external.ViaCepResponseDTO;

public interface IViaCep {
    ViaCepResponseDTO getCep(String cep);
}

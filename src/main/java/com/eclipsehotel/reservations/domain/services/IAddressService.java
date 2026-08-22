package com.eclipsehotel.reservations.domain.services;

import com.eclipsehotel.reservations.controller.dto.external.CepResponseDTO;

public interface IAddressService {
    CepResponseDTO findByCep(String cep);
}

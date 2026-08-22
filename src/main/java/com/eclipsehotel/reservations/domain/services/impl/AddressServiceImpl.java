package com.eclipsehotel.reservations.domain.services.impl;

import com.eclipsehotel.reservations.config.CacheConfig;
import com.eclipsehotel.reservations.controller.dto.external.CepResponseDTO;
import com.eclipsehotel.reservations.domain.services.IAddressService;
import com.eclipsehotel.reservations.infra.api.IBrasilApiClient;
import com.eclipsehotel.reservations.infra.exceptions.CepNotFoundException;
import com.eclipsehotel.reservations.infra.exceptions.GlobalExeceptionsHandler;
import com.eclipsehotel.reservations.infra.exceptions.InvalidDataException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements IAddressService {


    private final IBrasilApiClient brasilApi;
    private static final Pattern CEP_PATTERN = Pattern.compile("^\\d{8}$");

    @Override
    @Cacheable(value = CacheConfig.CEP_ADDRESS_CACHE, key = "#cep")
    public CepResponseDTO findByCep(String cep) {

        if(cep == null || !CEP_PATTERN.matcher(cep).matches()) {
            throw new InvalidDataException("CEP deve conter exatamente 8 dígitos numéricos");
        }

        CepResponseDTO response;
        try {
             response = brasilApi.findByCep(cep);
        }
        catch (FeignException.NotFound e) {
            throw new CepNotFoundException("CEP não encontrado");
        }
        catch (FeignException e) {
            throw new GlobalExeceptionsHandler.ExternalServiceException("Serviço de consulta de CEP indisponível no momento");
        }

        return response;
    }
}

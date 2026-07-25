package com.eclipsehotel.reservations.domain.services.impl;

import com.eclipsehotel.reservations.controller.dto.external.ViaCepResponseDTO;
import com.eclipsehotel.reservations.domain.services.IViaCep;
import com.eclipsehotel.reservations.infra.exceptions.GlobalExeceptionsHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ViaCepServiceImpl implements IViaCep {

    private final WebClient webClient;

    @Override
    public ViaCepResponseDTO getCep(String cep) {

        return webClient
                .get()
                .uri("/{cep}/json/", cep)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        Mono.error(new GlobalExeceptionsHandler.ExternalServiceException("ViaCEP is not responding"))
                )
                .bodyToMono(ViaCepResponseDTO.class)
                .block();
    }
}

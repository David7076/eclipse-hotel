package com.eclipsehotel.reservations.infra.api;

import com.eclipsehotel.reservations.controller.dto.external.CepResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "brasilApiClient", url = "${brasilapi.cep.base-url}")
public interface IBrasilApiClient {

    @GetMapping("/cep/v1/{cep}")
    CepResponseDTO findByCep(@PathVariable("cep") String cep);
}

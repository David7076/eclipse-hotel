package com.eclipsehotel.reservations.controller.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ViaCepResponseDTO(
        @JsonProperty("cep")
        String zipCode,

        @JsonProperty("logradouro")
        String street,

        @JsonProperty("bairro")
        String neighborhood,

        @JsonProperty("localidade")
        String city,

        @JsonProperty("uf")
        String state,

        @JsonProperty("erro")
        Boolean error
) {

}

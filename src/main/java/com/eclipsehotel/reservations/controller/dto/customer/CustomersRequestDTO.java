package com.eclipsehotel.reservations.controller.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record CustomersRequestDTO(
         @NotBlank(message = "Necessário informar o nome do Cliente! ")
         String name,

         @Email(message = "Email inserido inválido")
         String email,

         @NotBlank(message = "Necessário informar o telefone")
         @Pattern(regexp = "^\\d{8,9}$", message = "O número deve conter apenas 8 ou 9 dígitos")
         String phone,

         @NotNull
         LocalDate create_at,

         @NotBlank
         String zipCode,

         @NotBlank
         String number,

         String addressDetails

) {
}

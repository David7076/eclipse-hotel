package com.eclipsehotel.reservations.controller;

import com.eclipsehotel.reservations.controller.dto.customer.CustomersRequestDTO;
import com.eclipsehotel.reservations.controller.dto.customer.CustomerUpdateRequestDTO;
import com.eclipsehotel.reservations.controller.dto.customer.CustomersResponseDTO;
import com.eclipsehotel.reservations.controller.dto.customer.CustomerResponseDetailDTO;
import com.eclipsehotel.reservations.domain.services.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.eclipsehotel.reservations.domain.services.impl.CustomersServiceImpl;

@RestController
@RequestMapping("/customers")
public class CustomersController implements ICustomerService {

    private final CustomersServiceImpl service;

    public CustomersController(CustomersServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<CustomersResponseDTO> createCustomers(
            @RequestBody @Valid CustomersRequestDTO dto,
            UriComponentsBuilder uriBuilder) {
        var response = service.saveCustomer(dto);
        var uri = uriBuilder.path("/customers/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<CustomersResponseDTO>> getAllCustomers(@PageableDefault Pageable pagination) {
        var page = service.listAllCustomers(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/getCustomer/{id}")
    public ResponseEntity<CustomerResponseDetailDTO> getByIdCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(service.getByIdCustomer(id));
    }

    @PutMapping("/updateCustomer/{id}")
    @Transactional
    public ResponseEntity<CustomersResponseDTO> updateCustomer(
            @RequestBody
            @PathVariable Long id,
            @Valid CustomerUpdateRequestDTO dto)
    {
        CustomersResponseDTO response = service.update(dto, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

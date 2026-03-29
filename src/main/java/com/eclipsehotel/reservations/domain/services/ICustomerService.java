package com.eclipsehotel.reservations.domain.services;

import com.eclipsehotel.reservations.controller.dto.customer.CustomerResponseDetailDTO;
import com.eclipsehotel.reservations.controller.dto.customer.CustomerUpdateRequestDTO;
import com.eclipsehotel.reservations.controller.dto.customer.CustomersRequestDTO;
import com.eclipsehotel.reservations.controller.dto.customer.CustomersResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface ICustomerService {
    ResponseEntity<CustomersResponseDTO> createCustomers(CustomersRequestDTO dto, UriComponentsBuilder uriBuilder);

    ResponseEntity<Page<CustomersResponseDTO>> getAllCustomers(Pageable pagination);

    ResponseEntity<CustomerResponseDetailDTO> getByIdCustomer(Long id);

    ResponseEntity<CustomersResponseDTO> updateCustomer( Long id, CustomerUpdateRequestDTO dto);

    ResponseEntity<String> deleteCustomer(Long id);


}

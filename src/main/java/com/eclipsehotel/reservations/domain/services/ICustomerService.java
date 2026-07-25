package com.eclipsehotel.reservations.domain.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import com.eclipsehotel.reservations.controller.dto.customer.CustomerResponseDetailDTO;
import com.eclipsehotel.reservations.controller.dto.customer.CustomerUpdateRequestDTO;
import com.eclipsehotel.reservations.controller.dto.customer.CustomersRequestDTO;
import com.eclipsehotel.reservations.controller.dto.customer.CustomersResponseDTO;

public interface ICustomerService {

    CustomersResponseDTO saveCustomer(CustomersRequestDTO dto);

    Page<CustomersResponseDTO> listAllCustomers(Pageable pagination);

    CustomerResponseDetailDTO getByIdCustomer(Long id);

    CustomersResponseDTO update(CustomerUpdateRequestDTO dto, Long id);

    void delete(Long id);

}

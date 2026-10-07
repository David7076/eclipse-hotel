package com.eclipsehotel.reservations.domain.services;

import com.eclipsehotel.reservations.domain.models.CustomersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.eclipsehotel.reservations.controller.dto.customer.CustomerUpdateRequestDTO;
import com.eclipsehotel.reservations.controller.dto.customer.CustomersRequestDTO;

public interface ICustomerService {

    CustomersEntity saveCustomer(CustomersRequestDTO dto);

    Page<CustomersEntity> listAllCustomers(Pageable pagination);

    CustomersEntity getByIdCustomer(Long id);

    CustomersEntity update(CustomerUpdateRequestDTO dto, Long id);

    void delete(Long id);

}

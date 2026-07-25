package com.eclipsehotel.reservations.domain.services.impl;

import com.eclipsehotel.reservations.infra.exceptions.EntityNotFoundExcetpion;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.eclipsehotel.reservations.controller.dto.customer.CustomerResponseDetailDTO;
import com.eclipsehotel.reservations.controller.dto.customer.CustomerUpdateRequestDTO;
import com.eclipsehotel.reservations.controller.dto.customer.CustomersRequestDTO;
import com.eclipsehotel.reservations.controller.dto.customer.CustomersResponseDTO;
import com.eclipsehotel.reservations.domain.mapper.AddressMapper;
import com.eclipsehotel.reservations.domain.mapper.CustomersMapper;
import com.eclipsehotel.reservations.domain.models.AddressEntity;
import com.eclipsehotel.reservations.domain.models.CustomersEntity;
import com.eclipsehotel.reservations.domain.services.ICustomerService;
import com.eclipsehotel.reservations.infra.exceptions.GlobalExeceptions;
import com.eclipsehotel.reservations.infra.repository.CustomersRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomersServiceImpl implements ICustomerService {

    private final CustomersRepository repository;
    private final ViaCepServiceImpl cepService;

    public CustomersServiceImpl(CustomersRepository repository, ViaCepServiceImpl cepService) {
        this.repository = repository;
        this.cepService = cepService;
    }

    @Override
    @Transactional
    public CustomersResponseDTO saveCustomer(CustomersRequestDTO dto) {
        CustomersEntity entityCustomer = CustomersMapper.toEntity(dto);
        var responseCep = cepService.getCep(dto.zipCode());
        log.info("Dados retornados da API");

        if (responseCep.error() != null && Boolean.TRUE.equals(responseCep.error())) {
            throw new GlobalExeceptions.CepNotFoundException(dto.zipCode());
        }

        AddressEntity entityAddress = AddressMapper.toEntity(responseCep);
        entityAddress.setNumber(dto.number());
        entityAddress.setAddressDetails(dto.addressDetails());
        entityCustomer.setAddress(entityAddress);
        repository.save(entityCustomer);
        log.info("Cliente criado com sucesso! ");
        return CustomersMapper.toDTO(entityCustomer);
    }

    @Override
    public Page<CustomersResponseDTO> listAllCustomers(Pageable pageable) {
        return repository.findAll(pageable).map(CustomersResponseDTO::new);
    }

    @Override
    public CustomerResponseDetailDTO getByIdCustomer(@PathVariable Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundExcetpion("Cliente não encontrado"));
        return new CustomerResponseDetailDTO(
                entity.getName(),
                entity.getEmail(),
                entity.getPhone()
        );
    }

    @Override
    @Transactional
    public CustomersResponseDTO update(CustomerUpdateRequestDTO dto, Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundExcetpion("Cliente não encontrado"));

        var entityUpdated = updateCustomer(dto, entity);

        repository.save(entityUpdated);
        log.info("Objeto cliente alterado com sucesso! ");
        return CustomersMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
        log.info("Cliente excluído com sucesso!");
    }

    private CustomersEntity updateCustomer(CustomerUpdateRequestDTO dto, CustomersEntity entity) {
        if (dto.name() != null) {
            entity.setName(dto.name());
        }
        if (dto.email() != null) {
            entity.setEmail(dto.email());
        }
        if (dto.phone() != null) {
            entity.setPhone(dto.phone());
        }
        AddressEntity address = new AddressEntity();
        if (!dto.zipCode().equals(entity.getAddress().getZipCode())) {
            var response = cepService.getCep(dto.zipCode());

            if (response.error() != null && Boolean.TRUE.equals(response.error())) {
                throw new GlobalExeceptions.CepNotFoundException(dto.zipCode());
            }

            address.setZipCode(response.zipCode());
            address.setNeighborhood(response.neighborhood());
            address.setStreet(response.street());
            address.setState(response.state());
            address.setCity(response.city());
        }
        if (dto.number() != null) {
            address.setNumber(dto.number());
        }
        if (dto.addressDetails() != null) {
            address.setAddressDetails(dto.addressDetails());
        }
        return entity;
    }

}

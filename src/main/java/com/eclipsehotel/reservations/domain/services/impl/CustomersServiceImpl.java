package com.eclipsehotel.reservations.domain.services.impl;

import com.eclipsehotel.reservations.infra.exceptions.EntityNotFoundExcetpion;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.eclipsehotel.reservations.controller.dto.customer.CustomerUpdateRequestDTO;
import com.eclipsehotel.reservations.controller.dto.customer.CustomersRequestDTO;
import com.eclipsehotel.reservations.domain.mapper.AddressMapper;
import com.eclipsehotel.reservations.domain.mapper.CustomersMapper;
import com.eclipsehotel.reservations.domain.models.AddressEntity;
import com.eclipsehotel.reservations.domain.models.CustomersEntity;
import com.eclipsehotel.reservations.domain.services.ICustomerService;
import com.eclipsehotel.reservations.infra.repository.CustomersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomersServiceImpl implements ICustomerService {

    private final CustomersRepository repository;
    private final AddressServiceImpl cepService;

    public CustomersServiceImpl(CustomersRepository repository, AddressServiceImpl cepService) {
        this.repository = repository;
        this.cepService = cepService;
    }

    @Override
    @Transactional
    public CustomersEntity saveCustomer(CustomersRequestDTO dto) {
        CustomersEntity entityCustomer = CustomersMapper.toEntity(dto);
        var responseCep = cepService.findByCep(dto.cep());
        log.info("Dados retornados da API");

        AddressEntity entityAddress = AddressMapper.toEntity(responseCep);
        entityAddress.setNumber(dto.number());
        entityAddress.setAddressDetails(dto.addressDetails());
        entityCustomer.setAddress(entityAddress);
        var entity = repository.save(entityCustomer);
        log.info("Cliente criado com sucesso! ");

        return entity;
    }

    @Override
    public Page<CustomersEntity> listAllCustomers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public CustomersEntity getByIdCustomer(@PathVariable Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundExcetpion("Cliente não encontrado"));
        return entity;
    }

    @Override
    @Transactional
    public CustomersEntity update(CustomerUpdateRequestDTO dto, Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundExcetpion("Cliente não encontrado"));

        var entityUpdated = updateCustomer(dto, entity);

        repository.save(entityUpdated);
        log.info("Objeto cliente alterado com sucesso! ");
        return entityUpdated;
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
        if (!StringUtils.isEmpty(dto.cep()) || !dto.cep().equals(entity.getAddress().getCep())) {
            var response = cepService.findByCep(dto.cep());

            address.setCep(response.cep());
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

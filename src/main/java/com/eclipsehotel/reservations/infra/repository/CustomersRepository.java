package com.eclipsehotel.reservations.infra.repository;

import com.eclipsehotel.reservations.domain.models.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<CustomersEntity, Long> {
}

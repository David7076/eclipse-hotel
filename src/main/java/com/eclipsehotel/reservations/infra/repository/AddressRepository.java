package com.eclipsehotel.reservations.infra.repository;

import com.eclipsehotel.reservations.domain.models.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}

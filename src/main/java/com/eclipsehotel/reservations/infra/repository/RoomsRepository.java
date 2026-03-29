package com.eclipsehotel.reservations.infra.repository;

import com.eclipsehotel.reservations.domain.models.RoomsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomsRepository extends JpaRepository<RoomsEntity, Long> {
}

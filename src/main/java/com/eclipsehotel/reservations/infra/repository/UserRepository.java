package com.eclipsehotel.reservations.infra.repository;

import com.eclipsehotel.reservations.domain.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}

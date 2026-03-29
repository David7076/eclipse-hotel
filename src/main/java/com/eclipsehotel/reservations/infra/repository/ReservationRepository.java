package com.eclipsehotel.reservations.infra.repository;

import com.eclipsehotel.reservations.domain.models.ReservationsEntity;
import com.eclipsehotel.reservations.domain.models.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface ReservationRepository extends JpaRepository<ReservationsEntity, Long> {

    @Query("""
       SELECT r FROM ReservationsEntity r
       WHERE r.room.id = :roomId
       AND r.status <> 'CANCELED'
       AND (
            (r.checkin <= :checkout AND r.checkout >= :checkin)
       )
       """)

    List<ReservationsEntity> findConflictingReservations(Long roomId, LocalDate checkin, LocalDate checkout);

    @Query("""
    SELECT r FROM ReservationsEntity r
    WHERE r.checkin <= :endDate
    AND r.checkout >= :startDate
    """)
    List<ReservationsEntity> findReservationsByDateRange(LocalDate startDate, LocalDate endDate);

    List<ReservationsEntity> findByStatus(ReservationStatus status);

}

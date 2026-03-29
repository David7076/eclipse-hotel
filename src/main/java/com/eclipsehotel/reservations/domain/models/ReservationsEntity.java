package com.eclipsehotel.reservations.domain.models;

import com.eclipsehotel.reservations.domain.models.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class ReservationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomersEntity customer;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomsEntity room;

    private LocalDate checkin;
    private LocalDate checkout;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
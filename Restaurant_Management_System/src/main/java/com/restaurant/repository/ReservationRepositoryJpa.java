package com.restaurant.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.entity.Reservation;
import com.restaurant.enums.ReservationStatus;

@Repository
public interface ReservationRepositoryJpa extends JpaRepository<Reservation, Long> {

    List<Reservation> findByStatus(ReservationStatus status);

    List<Reservation> findByTableId(Long tableId);

    List<Reservation> findByReservationDate(LocalDate reservationDate);

    List<Reservation> findByReservationDateBetween(LocalDate startDate, LocalDate endDate);
}

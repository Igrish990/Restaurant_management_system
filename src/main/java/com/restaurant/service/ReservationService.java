package com.restaurant.service;

import java.time.LocalDate;
import java.util.List;

import com.restaurant.dto.AddReservationDto;
import com.restaurant.dto.ReservationResponseDto;
import com.restaurant.entity.Reservation;

public interface ReservationService {

    List<ReservationResponseDto> getAllReservations();

    String createReservation(AddReservationDto dto);

    Reservation getReservationById(Long id);

    Reservation updateReservation(Long id, AddReservationDto dto);

    Reservation deleteReservation(Long id);

    List<ReservationResponseDto> getReservationsByTableId(Long tableId);

    List<ReservationResponseDto> getReservationsByDate(LocalDate date);
}

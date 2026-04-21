package com.restaurant.mapping;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.restaurant.dto.AddReservationDto;
import com.restaurant.dto.ReservationResponseDto;
import com.restaurant.entity.Reservation;
import com.restaurant.entity.RestaurantTable;
import com.restaurant.enums.ReservationStatus;

@Component
public class ReservationMapper {
	public Reservation getReservationFromAddReservationDtoMapper(AddReservationDto dto, RestaurantTable table) {
		return Reservation.builder().table(table).guestName(dto.getGuestName()).guestEmail(dto.getGuestEmail())
				.guestPhone(dto.getGuestPhone()).guestCount(dto.getGuestCount())
				.reservationDate(dto.getReservationDate()).reservationTime(dto.getReservationTime())
				.status(ReservationStatus.CONFIRMED).notes(dto.getNotes()).createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now()).build();
	}

	public ReservationResponseDto entityToReservationResponseDtoMapper(Reservation reservation) {
		return ReservationResponseDto.builder().id(reservation.getId()).tableId(reservation.getTable().getId())
				.tableNumber(reservation.getTable().getTableNumber()).guestName(reservation.getGuestName())
				.guestEmail(reservation.getGuestEmail()).guestPhone(reservation.getGuestPhone())
				.guestCount(reservation.getGuestCount()).reservationDate(reservation.getReservationDate())
				.reservationTime(reservation.getReservationTime()).status(reservation.getStatus())
				.notes(reservation.getNotes()).build();
	}
}

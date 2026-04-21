package com.restaurant.serviceimpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.dto.AddReservationDto;
import com.restaurant.dto.ReservationResponseDto;
import com.restaurant.entity.Reservation;
import com.restaurant.entity.RestaurantTable;
import com.restaurant.mapping.ReservationMapper;
import com.restaurant.repository.ReservationRepositoryJpa;
import com.restaurant.repository.TableRepository;
import com.restaurant.service.ReservationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepositoryJpa reservationRepo;
    private final TableRepository tableRepo;
    private final ReservationMapper reservationMapper;

    @Override
    public List<ReservationResponseDto> getAllReservations() {
        return reservationRepo.findAll().stream()
                .map(reservationMapper::entityToReservationResponseDtoMapper).toList();
    }

    @Override
    @Transactional
    public String createReservation(AddReservationDto dto) {
        RestaurantTable table = tableRepo.findById(dto.getTableId())
                .orElseThrow(() -> new NoSuchElementException("Table not found with id: " + dto.getTableId()));

        Reservation reservation = reservationMapper.getReservationFromAddReservationDtoMapper(dto, table);
        reservation = reservationRepo.save(reservation);
        String response = "Reservation saved with id: " + reservation.getId();
        return response;
    }

    @Override
    public Reservation getReservationById(Long id) {
        Optional<Reservation> optReservation = reservationRepo.findById(id);
        if (optReservation.isEmpty()) {
            throw new NoSuchElementException("No reservation found with id: " + id);
        }
        return optReservation.get();
    }

    @Override
    @Transactional
    public Reservation updateReservation(Long id, AddReservationDto dto) {
        Reservation reservation = getReservationById(id);

        reservation.setGuestName(dto.getGuestName());
        reservation.setGuestEmail(dto.getGuestEmail());
        reservation.setGuestPhone(dto.getGuestPhone());
        reservation.setGuestCount(dto.getGuestCount());
        reservation.setReservationDate(dto.getReservationDate());
        reservation.setReservationTime(dto.getReservationTime());
        reservation.setNotes(dto.getNotes());
        reservation.setUpdatedAt(LocalDateTime.now());

        Reservation updated = reservationRepo.save(reservation);
        return updated;
    }

    @Override
    @Transactional
    public Reservation deleteReservation(Long id) {
        Reservation reservation = getReservationById(id);
        reservationRepo.delete(reservation);
        return reservation;
    }

    @Override
    public List<ReservationResponseDto> getReservationsByTableId(Long tableId) {
        return reservationRepo.findByTableId(tableId).stream()
                .map(reservationMapper::entityToReservationResponseDtoMapper).toList();
    }

    @Override
    public List<ReservationResponseDto> getReservationsByDate(LocalDate date) {
        return reservationRepo.findByReservationDate(date).stream()
                .map(reservationMapper::entityToReservationResponseDtoMapper).toList();
    }
}

package com.restaurant.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.dto.AddReservationDto;
import com.restaurant.dto.ApiResponse;
import com.restaurant.dto.ReservationResponseDto;
import com.restaurant.entity.Reservation;
import com.restaurant.mapping.ReservationMapper;
import com.restaurant.service.ReservationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1.0/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    @PostMapping
    public ResponseEntity<ApiResponse> addReservation(@Valid @RequestBody AddReservationDto dto) {
        String serviceResponse = reservationService.createReservation(dto);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("string")
                .payload(serviceResponse)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllReservations() {
        List<ReservationResponseDto> reservations = reservationService.getAllReservations();
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("list")
                .payload(reservations)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getReservationById(@PathVariable("id") Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        ReservationResponseDto dto = reservationMapper.entityToReservationResponseDtoMapper(reservation);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("object")
                .payload(dto)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/table/{tableId}")
    public ResponseEntity<ApiResponse> getReservationsByTableId(@PathVariable("tableId") Long tableId) {
        List<ReservationResponseDto> reservations = reservationService.getReservationsByTableId(tableId);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("list")
                .payload(reservations)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<ApiResponse> getReservationsByDate(@RequestParam LocalDate date) {
        List<ReservationResponseDto> reservations = reservationService.getReservationsByDate(date);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("list")
                .payload(reservations)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateReservation(@PathVariable("id") Long id,
            @Valid @RequestBody AddReservationDto dto) {
        Reservation reservation = reservationService.updateReservation(id, dto);
        ReservationResponseDto response = reservationMapper.entityToReservationResponseDtoMapper(reservation);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("object")
                .payload(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteReservation(@PathVariable("id") Long id) {
        Reservation reservation = reservationService.deleteReservation(id);
        ReservationResponseDto response = reservationMapper.entityToReservationResponseDtoMapper(reservation);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("object")
                .payload(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}

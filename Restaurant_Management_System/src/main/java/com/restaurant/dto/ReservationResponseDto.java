package com.restaurant.dto;

import java.time.LocalDate;

import com.restaurant.enums.ReservationStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponseDto {
    private Long id;
    private Long tableId;
    private String tableNumber;
    private String guestName;
    private String guestEmail;
    private String guestPhone;
    private Integer guestCount;
    private LocalDate reservationDate;
    private String reservationTime;
    private ReservationStatus status;
    private String notes;
}

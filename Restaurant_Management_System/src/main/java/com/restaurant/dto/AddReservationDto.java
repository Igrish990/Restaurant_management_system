package com.restaurant.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddReservationDto {
    @NotNull(message = "Table ID can't be null")
    private Long tableId;

    @NotBlank(message = "Guest Name can't be null or empty")
    private String guestName;

    @Email(message = "Guest Email must be valid")
    private String guestEmail;

    @NotBlank(message = "Guest Phone can't be null or empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String guestPhone;

    @Positive(message = "Guest Count must be a positive number")
    private Integer guestCount;

    @NotNull(message = "Reservation Date can't be null")
    private LocalDate reservationDate;

    @NotBlank(message = "Reservation Time can't be null or empty")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Time must be in HH:mm format")
    private String reservationTime;

    private String notes;
}

package com.restaurant.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddOrderDto {
    @NotNull(message = "Table ID can't be null")
    private Long tableId;

    @NotBlank(message = "Order Type can't be null or empty")
    private String orderType;

    @PositiveOrZero(message = "Total Amount can't be negative")
    private BigDecimal totalAmount;

    private String notes;
}

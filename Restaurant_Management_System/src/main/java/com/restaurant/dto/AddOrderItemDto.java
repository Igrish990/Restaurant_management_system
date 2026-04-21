package com.restaurant.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddOrderItemDto {
    @NotNull(message = "Order ID can't be null")
    private Long orderId;

    @NotNull(message = "Menu Item ID can't be null")
    private Long menuItemId;

    @Positive(message = "Quantity must be a positive number")
    private Integer quantity;

    @PositiveOrZero(message = "Price can't be negative")
    private BigDecimal price;

    private String notes;
}

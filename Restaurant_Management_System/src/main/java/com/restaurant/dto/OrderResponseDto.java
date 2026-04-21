package com.restaurant.dto;

import java.math.BigDecimal;

import com.restaurant.enums.OrderStatus;

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
public class OrderResponseDto {
    private Long id;
    private Long tableId;
    private String tableNumber;
    private OrderStatus status;
    private String orderType;
    private BigDecimal totalAmount;
    private String notes;
}

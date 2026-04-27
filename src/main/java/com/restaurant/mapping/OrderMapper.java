package com.restaurant.mapping;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.restaurant.dto.AddOrderDto;
import com.restaurant.dto.OrderResponseDto;
import com.restaurant.entity.Order;
import com.restaurant.entity.RestaurantTable;
import com.restaurant.enums.OrderStatus;

@Component
public class OrderMapper {
    public Order getOrderFromAddOrderDtoMapper(AddOrderDto dto, RestaurantTable table) {
        return Order.builder()
                .table(table)
                .status(OrderStatus.PENDING)
                .orderType(dto.getOrderType())
                .totalAmount(dto.getTotalAmount())
                .notes(dto.getNotes())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public OrderResponseDto entityToOrderResponseDtoMapper(Order order) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .tableId(order.getTable().getId())
                .tableNumber(order.getTable().getTableNumber())
                .status(order.getStatus())
                .orderType(order.getOrderType())
                .totalAmount(order.getTotalAmount())
                .notes(order.getNotes())
                .build();
    }
}

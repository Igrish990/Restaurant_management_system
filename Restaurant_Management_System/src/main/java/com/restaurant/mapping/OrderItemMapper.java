package com.restaurant.mapping;

import org.springframework.stereotype.Component;

import com.restaurant.dto.AddOrderItemDto;
import com.restaurant.dto.OrderItemResponseDto;
import com.restaurant.entity.MenuItem;
import com.restaurant.entity.Order;
import com.restaurant.entity.OrderItem;
import com.restaurant.enums.OrderItemStatus;

@Component
public class OrderItemMapper {
    public OrderItem getOrderItemFromAddOrderItemDtoMapper(AddOrderItemDto dto, Order order, MenuItem menuItem) {
        return OrderItem.builder()
                .order(order)
                .menuItem(menuItem)
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .notes(dto.getNotes())
                .status(OrderItemStatus.PENDING)
                .build();
    }

    public OrderItemResponseDto entityToOrderItemResponseDtoMapper(OrderItem orderItem) {
        return OrderItemResponseDto.builder()
                .id(orderItem.getId())
                .orderId(orderItem.getOrder().getId())
                .menuItemId(orderItem.getMenuItem().getId())
                .menuItemName(orderItem.getMenuItem().getName())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .notes(orderItem.getNotes())
                .status(orderItem.getStatus())
                .build();
    }
}

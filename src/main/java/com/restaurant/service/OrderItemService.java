package com.restaurant.service;

import java.util.List;

import com.restaurant.dto.AddOrderItemDto;
import com.restaurant.dto.OrderItemResponseDto;
import com.restaurant.entity.OrderItem;

public interface OrderItemService {

    List<OrderItemResponseDto> getAllOrderItems();

    String createOrderItem(AddOrderItemDto dto);

    OrderItem getOrderItemById(Long id);

    OrderItem updateOrderItem(Long id, AddOrderItemDto dto);

    OrderItem deleteOrderItem(Long id);

    List<OrderItemResponseDto> getOrderItemsByOrderId(Long orderId);
}

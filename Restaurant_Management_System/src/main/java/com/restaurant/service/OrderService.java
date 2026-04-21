package com.restaurant.service;

import java.util.List;

import com.restaurant.dto.AddOrderDto;
import com.restaurant.dto.OrderResponseDto;
import com.restaurant.entity.Order;

public interface OrderService {

    List<OrderResponseDto> getAllOrders();

    String createOrder(AddOrderDto dto);

    Order getOrderById(Long id);

    Order updateOrder(Long id, AddOrderDto dto);

    Order deleteOrder(Long id);

    List<OrderResponseDto> getOrdersByTableId(Long tableId);
}

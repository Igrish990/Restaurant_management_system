package com.restaurant.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.dto.AddOrderDto;
import com.restaurant.dto.OrderResponseDto;
import com.restaurant.entity.Order;
import com.restaurant.entity.RestaurantTable;
import com.restaurant.mapping.OrderMapper;
import com.restaurant.repository.OrderRepository;
import com.restaurant.repository.TableRepository;
import com.restaurant.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final TableRepository tableRepo;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderRepo.findAll().stream()
                .map(orderMapper::entityToOrderResponseDtoMapper).toList();
    }

    @Override
    @Transactional
    public String createOrder(AddOrderDto dto) {
        RestaurantTable table = tableRepo.findById(dto.getTableId())
                .orElseThrow(() -> new NoSuchElementException("Table not found with id: " + dto.getTableId()));

        Order order = orderMapper.getOrderFromAddOrderDtoMapper(dto, table);
        order = orderRepo.save(order);
        String response = "Order saved with id: " + order.getId();
        return response;
    }

    @Override
    public Order getOrderById(Long id) {
        Optional<Order> optOrder = orderRepo.findById(id);
        if (optOrder.isEmpty()) {
            throw new NoSuchElementException("No order found with id: " + id);
        }
        return optOrder.get();
    }

    @Override
    @Transactional
    public Order updateOrder(Long id, AddOrderDto dto) {
        Order order = getOrderById(id);

        order.setOrderType(dto.getOrderType());
        order.setTotalAmount(dto.getTotalAmount());
        order.setNotes(dto.getNotes());
        order.setUpdatedAt(LocalDateTime.now());

        Order updated = orderRepo.save(order);
        return updated;
    }

    @Override
    @Transactional
    public Order deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepo.delete(order);
        return order;
    }

    @Override
    public List<OrderResponseDto> getOrdersByTableId(Long tableId) {
        return orderRepo.findByTableId(tableId).stream()
                .map(orderMapper::entityToOrderResponseDtoMapper).toList();
    }
}

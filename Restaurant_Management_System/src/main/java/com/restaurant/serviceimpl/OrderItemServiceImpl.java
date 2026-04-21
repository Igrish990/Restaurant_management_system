package com.restaurant.serviceimpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.dto.AddOrderItemDto;
import com.restaurant.dto.OrderItemResponseDto;
import com.restaurant.entity.MenuItem;
import com.restaurant.entity.Order;
import com.restaurant.entity.OrderItem;
import com.restaurant.mapping.OrderItemMapper;
import com.restaurant.repository.MenuItemRepository;
import com.restaurant.repository.OrderItemRepository;
import com.restaurant.repository.OrderRepository;
import com.restaurant.service.OrderItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepo;
    private final OrderRepository orderRepo;
    private final MenuItemRepository menuItemRepo;
    private final OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemResponseDto> getAllOrderItems() {
        return orderItemRepo.findAll().stream()
                .map(orderItemMapper::entityToOrderItemResponseDtoMapper).toList();
    }

    @Override
    @Transactional
    public String createOrderItem(AddOrderItemDto dto) {
        Order order = orderRepo.findById(dto.getOrderId())
                .orElseThrow(() -> new NoSuchElementException("Order not found with id: " + dto.getOrderId()));

        MenuItem menuItem = menuItemRepo.findById(dto.getMenuItemId())
                .orElseThrow(() -> new NoSuchElementException("Menu Item not found with id: " + dto.getMenuItemId()));

        OrderItem orderItem = orderItemMapper.getOrderItemFromAddOrderItemDtoMapper(dto, order, menuItem);
        orderItem = orderItemRepo.save(orderItem);
        String response = "Order Item saved with id: " + orderItem.getId();
        return response;
    }

    @Override
    public OrderItem getOrderItemById(Long id) {
        Optional<OrderItem> optOrderItem = orderItemRepo.findById(id);
        if (optOrderItem.isEmpty()) {
            throw new NoSuchElementException("No order item found with id: " + id);
        }
        return optOrderItem.get();
    }

    @Override
    @Transactional
    public OrderItem updateOrderItem(Long id, AddOrderItemDto dto) {
        OrderItem orderItem = getOrderItemById(id);

        orderItem.setQuantity(dto.getQuantity());
        orderItem.setPrice(dto.getPrice());
        orderItem.setNotes(dto.getNotes());

        OrderItem updated = orderItemRepo.save(orderItem);
        return updated;
    }

    @Override
    @Transactional
    public OrderItem deleteOrderItem(Long id) {
        OrderItem orderItem = getOrderItemById(id);
        orderItemRepo.delete(orderItem);
        return orderItem;
    }

    @Override
    public List<OrderItemResponseDto> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepo.findByOrderId(orderId).stream()
                .map(orderItemMapper::entityToOrderItemResponseDtoMapper).toList();
    }
}

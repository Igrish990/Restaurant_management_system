package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.entity.OrderItem;
import com.restaurant.enums.OrderItemStatus;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	List<OrderItem> findByOrderId(Long orderId);

	List<OrderItem> findByStatus(OrderItemStatus status);

	List<OrderItem> findByMenuItemId(Long menuItemId);
}

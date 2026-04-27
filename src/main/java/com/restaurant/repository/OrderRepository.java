package com.restaurant.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.entity.Order;
import com.restaurant.enums.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByTableId(Long tableId);

    List<Order> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}

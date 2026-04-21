package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.entity.RestaurantTable;
import com.restaurant.enums.TableStatus;


@Repository
public interface TableRepository extends JpaRepository<RestaurantTable, Long> {

    List<RestaurantTable> findByStatus(TableStatus status);

    List<RestaurantTable> findByCapacityGreaterThanEqual(Integer capacity);
}

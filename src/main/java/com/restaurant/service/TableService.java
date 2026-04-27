package com.restaurant.service;

import java.util.List;

import com.restaurant.dto.AddTableDto;
import com.restaurant.dto.TableResponseDto;
import com.restaurant.entity.RestaurantTable;


public interface TableService {

    List<TableResponseDto> getAllTables();

    String createTable(AddTableDto dto);

    RestaurantTable getTableById(Long id);

    RestaurantTable updateTable(Long id, AddTableDto dto);

    RestaurantTable deleteTable(Long id);

    List<TableResponseDto> getAvailableTables();
}


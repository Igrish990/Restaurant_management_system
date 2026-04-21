package com.restaurant.mapping;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.restaurant.dto.AddTableDto;
import com.restaurant.dto.TableResponseDto;
import com.restaurant.entity.RestaurantTable;
import com.restaurant.enums.TableStatus;

@Component
public class TableMapper {
	public RestaurantTable getTableFromAddTableDtoMapper(AddTableDto dto) {
		return RestaurantTable.builder().tableNumber(dto.getTableNumber()).capacity(dto.getCapacity())
				.location(dto.getLocation()).status(TableStatus.AVAILABLE).createdAt(LocalDateTime.now()).build();
	}

	public TableResponseDto entityToTableResponseDtoMapper(RestaurantTable table) {
		return TableResponseDto.builder().id(table.getId()).tableNumber(table.getTableNumber())
				.capacity(table.getCapacity()).status(table.getStatus()).location(table.getLocation()).build();
	}
}

package com.restaurant.mapping;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.restaurant.dto.AddMenuItemDto;
import com.restaurant.dto.ItemResponseDto;
import com.restaurant.entity.Category;
import com.restaurant.entity.MenuItem;

@Component
public class ModelMapper {
	public MenuItem getItemFromAddMenuItemDtoMpaaer(AddMenuItemDto dto, Category category) {
		MenuItem item = MenuItem.builder().name(dto.getName()).description(dto.getDescription()).price(dto.getPrice())
				.imageUrl(dto.getImageUrl()).preparationTime(dto.getPreparationTime()).isAvailable(true)
				.createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).category(category).build();
		return item;
	}

	public ItemResponseDto entityToMenuItemResponseDtoMapper(MenuItem item) {
		return ItemResponseDto.builder().name(item.getName()).description(item.getDescription()).price(item.getPrice())
				.imageUrl(item.getImageUrl()).preparationTime(item.getPreparationTime()).build();
	}

}

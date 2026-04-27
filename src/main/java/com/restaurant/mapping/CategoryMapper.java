package com.restaurant.mapping;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.restaurant.dto.AddCategoryDto;
import com.restaurant.dto.CategoryResponseDto;
import com.restaurant.entity.Category;

@Component
public class CategoryMapper {
	public Category getCategoryFromAddCategoryDtoMapper(AddCategoryDto dto) {
		return Category.builder().name(dto.getName()).description(dto.getDescription()).sortOrder(dto.getSortOrder())
				.createdAt(LocalDateTime.now()).build();
	}

	public CategoryResponseDto entityToCategoryResponseDtoMapper(Category category) {
		return CategoryResponseDto.builder().id(category.getId()).name(category.getName())
				.description(category.getDescription()).sortOrder(category.getSortOrder()).build();
	}
}

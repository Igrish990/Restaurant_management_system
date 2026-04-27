package com.restaurant.service;

import java.util.List;

import com.restaurant.dto.AddCategoryDto;
import com.restaurant.dto.CategoryResponseDto;
import com.restaurant.entity.Category;

public interface CategoryService {
	public List<CategoryResponseDto> getAllCategoriesSorted();

	public String createCategory(AddCategoryDto dto);

	public Category updateCategory(Long id, AddCategoryDto dto);
}

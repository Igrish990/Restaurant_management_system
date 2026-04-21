package com.restaurant.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.dto.AddCategoryDto;
import com.restaurant.dto.CategoryResponseDto;
import com.restaurant.entity.Category;
import com.restaurant.mapping.CategoryMapper;
import com.restaurant.repository.CategoryRepository;
import com.restaurant.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepo;
	private final CategoryMapper categoryMapper;

	@Override
	public List<CategoryResponseDto> getAllCategoriesSorted() {
		return categoryRepo.findAllByOrderBySortOrderAsc().stream()
				.map(categoryMapper::entityToCategoryResponseDtoMapper).toList();
	}

	@Override
	@Transactional
	public String createCategory(AddCategoryDto dto) {
		Category category = categoryMapper.getCategoryFromAddCategoryDtoMapper(dto);
		category = categoryRepo.save(category);
		String response = "Category saved with id" + category.getId();
		return response;
	}

	@Override
	@Transactional
	public Category updateCategory(Long id, AddCategoryDto dto) {
		Category category = categoryRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

		category.setName(dto.getName());
		category.setDescription(dto.getDescription());
		category.setSortOrder(dto.getSortOrder());

		Category updated = categoryRepo.save(category);
		return updated;
	}
}

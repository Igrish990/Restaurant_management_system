package com.restaurant.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.dto.AddCategoryDto;
import com.restaurant.dto.ApiResponse;
import com.restaurant.dto.CategoryResponseDto;
import com.restaurant.dto.ItemResponseDto;
import com.restaurant.entity.Category;
import com.restaurant.service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1.0/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@PostMapping
	public ResponseEntity<ApiResponse> addCategory(@Valid @RequestBody AddCategoryDto dto) {
		String serviceResponse = categoryService.createCategory(dto);
		ApiResponse apiResponse = ApiResponse.builder().serviceName("RESTAURANT_SERVICE").status(true).type("string")
				.payload(serviceResponse).build();
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAllCategories() {
		List<CategoryResponseDto> categories = categoryService.getAllCategoriesSorted();
		ApiResponse apiResponse = ApiResponse.builder().serviceName("RESTAURANT_SERVICE").status(true).type("list")
				.payload(categories).build();
		return ResponseEntity.ok(apiResponse);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("id") Long id,
			@Valid @RequestBody AddCategoryDto dto) {
		Category category = categoryService.updateCategory(id, dto);
		CategoryResponseDto response = CategoryResponseDto.builder().id(category.getId()).name(category.getName())
				.description(category.getDescription()).sortOrder(category.getSortOrder()).build();

		ApiResponse apiResponse = ApiResponse.builder().serviceName("RESTAURANT_SERVICE").status(true).type("object")
				.payload(response).build();
		return ResponseEntity.ok(apiResponse);
	}

}

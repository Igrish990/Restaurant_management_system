package com.restaurant.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.dto.AddMenuItemDto;
import com.restaurant.dto.ApiResponse;
import com.restaurant.dto.ItemResponseDto;
import com.restaurant.entity.MenuItem;
import com.restaurant.mapping.ModelMapper;
import com.restaurant.service.MenuItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1.0/menu")
@RequiredArgsConstructor
public class MenuController {

	private final MenuItemService itemService;
	private final ModelMapper modelMapper;

	@PostMapping
	public ResponseEntity<ApiResponse> addNewItemController(@Valid @RequestBody AddMenuItemDto dto) {
		String serviceResponse = itemService.createMenuItem(dto);
		ApiResponse apiresponse = ApiResponse.builder().serviceName("RESTAURANT_SERVICE").status(true).type("string")
				.payload(serviceResponse).build();
		return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getItemByIdController(@PathVariable("id") Long id) {
		MenuItem item = itemService.getMenuItemById(id);
		ItemResponseDto dto = modelMapper.entityToMenuItemResponseDtoMapper(item);
		ApiResponse apiresponse = ApiResponse.builder().serviceName("RESTAURANT_SERVICE").status(true).type("object")
				.payload(dto).build();
		return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateItemByIdController(@PathVariable("id") Long id,
			@Valid @RequestBody AddMenuItemDto dto) {
		MenuItem item = itemService.updateMenuItemService(id, dto);
		ItemResponseDto response = modelMapper.entityToMenuItemResponseDtoMapper(item);
		ApiResponse apiresponse = ApiResponse.builder().serviceName("RESTAURANT_SERVICE").status(true).type("object")
				.payload(response).build();
		return ResponseEntity.ok(apiresponse);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteItemController(@PathVariable("id") Long id) {
		MenuItem item = itemService.deleteMenuItemService(id);
		ItemResponseDto response = modelMapper.entityToMenuItemResponseDtoMapper(item);
		ApiResponse apiresponse = ApiResponse.builder().serviceName("RESTAURANT_SERVICE").status(true).type("object")
				.payload(response).build();
		return ResponseEntity.ok(apiresponse);
	}

	@PatchMapping("/{id}/available")
	public ResponseEntity<ApiResponse> makeItemAvailableController(@PathVariable("id") Long id) {
		MenuItem item = itemService.makeItemAvailableService(id);
		ItemResponseDto response = modelMapper.entityToMenuItemResponseDtoMapper(item);
		ApiResponse apiresponse = ApiResponse.builder().serviceName("RESTAURANT_SERVICE").status(true).type("object")
				.payload(response).build();
		return ResponseEntity.ok(apiresponse);
	}

	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAllItemsController() {

		List<ItemResponseDto> items = itemService.getAllMenuItems();

		ApiResponse response = ApiResponse.builder().serviceName("RESTAURANT_SERVICE").status(true).type("list")
				.payload(items).build();

		return ResponseEntity.ok(response);
	}

}

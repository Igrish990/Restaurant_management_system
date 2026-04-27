package com.restaurant.service;

import java.util.List;

import com.restaurant.dto.AddMenuItemDto;
import com.restaurant.dto.ItemResponseDto;
import com.restaurant.entity.MenuItem;

public interface MenuItemService {

	List<ItemResponseDto> getAllMenuItems();

	String createMenuItem(AddMenuItemDto dto);

	MenuItem getMenuItemById(Long id);

	MenuItem updateMenuItemService(Long id, AddMenuItemDto dto);

	MenuItem deleteMenuItemService(Long id);

	MenuItem makeItemAvailableService(Long id);

}

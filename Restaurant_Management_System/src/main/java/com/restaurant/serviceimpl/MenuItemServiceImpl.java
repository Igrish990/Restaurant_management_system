package com.restaurant.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.dto.AddMenuItemDto;
import com.restaurant.dto.ItemResponseDto;
import com.restaurant.entity.Category;
import com.restaurant.entity.MenuItem;
import com.restaurant.mapping.ModelMapper;
import com.restaurant.repository.CategoryRepository;
import com.restaurant.repository.MenuItemRepository;
import com.restaurant.service.MenuItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
	private final MenuItemRepository itemRepo;
	private final CategoryRepository categoryRepo;
	private final ModelMapper modelmapper;

	@Override
	public List<ItemResponseDto> getAllMenuItems() {
		return itemRepo.findByIsAvailableTrue()
				.stream()
				.map(modelmapper::entityToMenuItemResponseDtoMapper)
				.toList();
	}

	@Override
	@Transactional
	public String createMenuItem(AddMenuItemDto dto) {
		Category category = categoryRepo.findById(dto.getCategoryId())
				.orElseThrow(() -> new NoSuchElementException("Category not found with id " + dto.getCategoryId()));
		MenuItem item = modelmapper.getItemFromAddMenuItemDtoMpaaer(dto, category);
		item = itemRepo.save(item);
		String response = "Item saved in menu with itme id " + item.getId();
		return response;
	}

	@Override
	@Cacheable(value = "menuitem", key = "#id")
	public MenuItem getMenuItemById(Long id) {

		Optional<MenuItem> optItem = itemRepo.findById(id);
		if (optItem.isEmpty()) {
			throw new NoSuchElementException("No item found with id " + id);
		}
		MenuItem item = optItem.get();
		if (!item.getIsAvailable()) {
			throw new NoSuchElementException("No item found with id " + id);
		}

		return item;
	}

	@Override
	@Transactional
	public MenuItem updateMenuItemService(Long id, AddMenuItemDto dto) {
		MenuItem item = getMenuItemById(id);
		item.setName(dto.getName());
		item.setDescription(dto.getDescription());
		item.setPrice(dto.getPrice());
		item.setImageUrl(dto.getImageUrl());
		item.setPreparationTime(dto.getPreparationTime());
		item.setUpdatedAt(LocalDateTime.now());
		item = itemRepo.save(item);
		return item;
	}

	@Override
	@Transactional
	public MenuItem deleteMenuItemService(Long id) {
		MenuItem item = getMenuItemById(id);
		item.setIsAvailable(false);
		item = itemRepo.save(item);
		return item;
	}

	@Override
	@Transactional
	public MenuItem makeItemAvailableService(Long id) {
		MenuItem item = getMenuItemById(id);
		item.setIsAvailable(true);
		item = itemRepo.save(item);
		return item;
	}
}

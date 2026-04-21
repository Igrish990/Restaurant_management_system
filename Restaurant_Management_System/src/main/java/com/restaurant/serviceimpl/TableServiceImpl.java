package com.restaurant.serviceimpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.dto.AddTableDto;
import com.restaurant.dto.TableResponseDto;
import com.restaurant.entity.RestaurantTable;
import com.restaurant.enums.TableStatus;
import com.restaurant.mapping.TableMapper;
import com.restaurant.repository.TableRepository;
import com.restaurant.service.TableService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepo;
    private final TableMapper tableMapper;

    @Override
    public List<TableResponseDto> getAllTables() {
        return tableRepo.findAll().stream()
                .map(tableMapper::entityToTableResponseDtoMapper).toList();
    }

    @Override
    @Transactional
    public String createTable(AddTableDto dto) {
        RestaurantTable table = tableMapper.getTableFromAddTableDtoMapper(dto);
        table = tableRepo.save(table);
        String response = "Table saved with id: " + table.getId();
        return response;
    }

    @Override
    public RestaurantTable getTableById(Long id) {
        Optional<RestaurantTable> optTable = tableRepo.findById(id);
        if (optTable.isEmpty()) {
            throw new NoSuchElementException("No table found with id: " + id);
        }
        return optTable.get();
    }

    @Override
    @Transactional
    public RestaurantTable updateTable(Long id, AddTableDto dto) {
        RestaurantTable table = getTableById(id);

        table.setTableNumber(dto.getTableNumber());
        table.setCapacity(dto.getCapacity());
        table.setLocation(dto.getLocation());

        RestaurantTable updated = tableRepo.save(table);
        return updated;
    }

    @Override
    @Transactional
    public RestaurantTable deleteTable(Long id) {
        RestaurantTable table = getTableById(id);
        tableRepo.delete(table);
        return table;
    }

    @Override
    public List<TableResponseDto> getAvailableTables() {
        return tableRepo.findByStatus(TableStatus.AVAILABLE).stream()
                .map(tableMapper::entityToTableResponseDtoMapper).toList();
    }
}

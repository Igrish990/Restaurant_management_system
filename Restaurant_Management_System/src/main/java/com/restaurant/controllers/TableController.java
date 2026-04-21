package com.restaurant.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.dto.AddTableDto;
import com.restaurant.dto.ApiResponse;
import com.restaurant.dto.TableResponseDto;
import com.restaurant.entity.RestaurantTable;
import com.restaurant.mapping.TableMapper;
import com.restaurant.service.TableService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1.0/tables")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;
    private final TableMapper tableMapper;

    @PostMapping
    public ResponseEntity<ApiResponse> addTable(@Valid @RequestBody AddTableDto dto) {
        String serviceResponse = tableService.createTable(dto);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("string")
                .payload(serviceResponse)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllTables() {
        List<TableResponseDto> tables = tableService.getAllTables();
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("list")
                .payload(tables)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<ApiResponse> getAvailableTables() {
        List<TableResponseDto> tables = tableService.getAvailableTables();
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("list")
                .payload(tables)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTableById(@PathVariable("id") Long id) {
        RestaurantTable table = tableService.getTableById(id);
        TableResponseDto dto = tableMapper.entityToTableResponseDtoMapper(table);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("object")
                .payload(dto)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTable(@PathVariable("id") Long id,
            @Valid @RequestBody AddTableDto dto) {
        RestaurantTable table = tableService.updateTable(id, dto);
        TableResponseDto response = tableMapper.entityToTableResponseDtoMapper(table);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("object")
                .payload(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTable(@PathVariable("id") Long id) {
        RestaurantTable table = tableService.deleteTable(id);
        TableResponseDto response = tableMapper.entityToTableResponseDtoMapper(table);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("object")
                .payload(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}

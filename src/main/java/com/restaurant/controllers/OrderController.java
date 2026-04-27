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

import com.restaurant.dto.AddOrderDto;
import com.restaurant.dto.ApiResponse;
import com.restaurant.dto.OrderResponseDto;
import com.restaurant.entity.Order;
import com.restaurant.mapping.OrderMapper;
import com.restaurant.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1.0/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<ApiResponse> addOrder(@Valid @RequestBody AddOrderDto dto) {
        String serviceResponse = orderService.createOrder(dto);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("string")
                .payload(serviceResponse)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllOrders() {
        List<OrderResponseDto> orders = orderService.getAllOrders();
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("list")
                .payload(orders)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable("id") Long id) {
        Order order = orderService.getOrderById(id);
        OrderResponseDto dto = orderMapper.entityToOrderResponseDtoMapper(order);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("object")
                .payload(dto)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/table/{tableId}")
    public ResponseEntity<ApiResponse> getOrdersByTableId(@PathVariable("tableId") Long tableId) {
        List<OrderResponseDto> orders = orderService.getOrdersByTableId(tableId);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("list")
                .payload(orders)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateOrder(@PathVariable("id") Long id,
            @Valid @RequestBody AddOrderDto dto) {
        Order order = orderService.updateOrder(id, dto);
        OrderResponseDto response = orderMapper.entityToOrderResponseDtoMapper(order);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("object")
                .payload(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable("id") Long id) {
        Order order = orderService.deleteOrder(id);
        OrderResponseDto response = orderMapper.entityToOrderResponseDtoMapper(order);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("object")
                .payload(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}

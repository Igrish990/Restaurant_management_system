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

import com.restaurant.dto.AddOrderItemDto;
import com.restaurant.dto.ApiResponse;
import com.restaurant.dto.OrderItemResponseDto;
import com.restaurant.entity.OrderItem;
import com.restaurant.mapping.OrderItemMapper;
import com.restaurant.service.OrderItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1.0/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;
    private final OrderItemMapper orderItemMapper;

    @PostMapping
    public ResponseEntity<ApiResponse> addOrderItem(@Valid @RequestBody AddOrderItemDto dto) {
        String serviceResponse = orderItemService.createOrderItem(dto);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("string")
                .payload(serviceResponse)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllOrderItems() {
        List<OrderItemResponseDto> orderItems = orderItemService.getAllOrderItems();
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("list")
                .payload(orderItems)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOrderItemById(@PathVariable("id") Long id) {
        OrderItem orderItem = orderItemService.getOrderItemById(id);
        OrderItemResponseDto dto = orderItemMapper.entityToOrderItemResponseDtoMapper(orderItem);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("object")
                .payload(dto)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse> getOrderItemsByOrderId(@PathVariable("orderId") Long orderId) {
        List<OrderItemResponseDto> orderItems = orderItemService.getOrderItemsByOrderId(orderId);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("list")
                .payload(orderItems)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateOrderItem(@PathVariable("id") Long id,
            @Valid @RequestBody AddOrderItemDto dto) {
        OrderItem orderItem = orderItemService.updateOrderItem(id, dto);
        OrderItemResponseDto response = orderItemMapper.entityToOrderItemResponseDtoMapper(orderItem);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("object")
                .payload(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteOrderItem(@PathVariable("id") Long id) {
        OrderItem orderItem = orderItemService.deleteOrderItem(id);
        OrderItemResponseDto response = orderItemMapper.entityToOrderItemResponseDtoMapper(orderItem);
        ApiResponse apiResponse = ApiResponse.builder()
                .serviceName("RESTAURANT_SERVICE")
                .status(true)
                .type("object")
                .payload(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}

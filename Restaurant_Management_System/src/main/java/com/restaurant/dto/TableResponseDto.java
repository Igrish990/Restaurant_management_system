package com.restaurant.dto;

import com.restaurant.enums.TableStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableResponseDto {
    private Long id;
    private String tableNumber;
    private Integer capacity;
    private TableStatus status;
    private String location;
}

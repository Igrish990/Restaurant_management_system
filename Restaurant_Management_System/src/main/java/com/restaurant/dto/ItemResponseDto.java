package com.restaurant.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponseDto {
	private String name;
	private String description;
	private String imageUrl;
	private BigDecimal price;
	private Integer preparationTime;
}

package com.restaurant.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddMenuItemDto {

	@NotBlank(message = "Item Name can't be null or empty")
	@Size(min = 3, max = 20, message = "Name length should be between 3 and 20")
	private String name;

	@NotBlank(message = "Item Description can't be null or empty")
	@Size(min = 10, max = 100, message = "Description length should be between 10 and 100")
	private String description;

	@NotBlank(message = "Image Url Can't be null or empty")
	private String imageUrl;

	@PositiveOrZero(message = "Item Price can't be in minus")
	private BigDecimal price;

	@PositiveOrZero(message = "Preparation time can't in minus")
	private Integer preparationTime;

	private Long categoryId;

}

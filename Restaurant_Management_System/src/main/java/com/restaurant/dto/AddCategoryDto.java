package com.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCategoryDto {
	@NotBlank(message = "Catagory Name can't be null or empty")
	@Size(min = 3, max = 20, message = "Name length should be between 3 and 20")
	private String name;

	@NotBlank(message = "Catagory Description can't be null or empty")
	@Size(min = 10, max = 100, message = "Description length should be between 10 and 100")
	private String description;

	private Integer sortOrder;
}

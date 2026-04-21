package com.restaurant.dto;

import com.restaurant.enums.TableStatus;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddTableDto {

	@NotBlank(message = "Table number can't be null or empty")
	private String tableNumber;

	@NotNull(message = "Capacity should not be null")
	@Min(value = 1, message = "Minimum capacity is 1")
	@Max(value = 8, message = "Maximum capacity is 8")
	private Integer capacity;

	@NotNull(message = "Table status is required")
	private TableStatus status;

	@NotBlank(message = "Location can't be null or empty")
	private String location;
}

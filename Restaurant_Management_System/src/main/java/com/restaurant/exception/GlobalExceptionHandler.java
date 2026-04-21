package com.restaurant.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.restaurant.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> 
	handleDtoValidationException(MethodArgumentNotValidException ex){

	    String errorMessage = ex.getBindingResult()
	            .getFieldErrors()
	            .stream()
	            .map(error -> error.getDefaultMessage())
	            .findFirst() // get first error (or collect all)
	            .orElse("Validation Error");

	    ApiResponse apiResponse = ApiResponse.builder()
	            .status(false)
	            .type("error")
	            .payload(errorMessage)
	            .build();

	    return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	//General exception handler
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> 
			generalExceptionHandler(Exception ex){
		ApiResponse apiResponse=ApiResponse.builder()
				.status(false).type("string").payload(ex.getMessage()).build();
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ApiResponse> 
			noSuchElementExceptionHandler(NoSuchElementException ex){
		ApiResponse apiResponse=ApiResponse.builder()
				.status(false).type("string").payload(ex.getMessage()).build();
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

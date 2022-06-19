package com.ecomm.user.controller.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import com.ecomm.user.model.ApiError;

@ControllerAdvice
public class UserServiceExceptionHandler {
	
	@ExceptionHandler({ResponseStatusException.class})
    public final ResponseEntity<ApiError> handleException(Exception ex) {
		ApiError apiError = new ApiError();
		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());
		apiError.setErrors(errors);
       return new ResponseEntity<ApiError>(apiError, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ApiError apiError = new ApiError();
		List<String> errorsList = new ArrayList<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	        errorsList.add(fieldName + " : " + errorMessage);
	    });
	    apiError.setErrors(errorsList);
	    return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
	}		

}

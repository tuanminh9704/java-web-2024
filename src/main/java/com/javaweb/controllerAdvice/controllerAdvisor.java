package com.javaweb.controllerAdvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.model.ErrorResponseDTO;

import customexception.FieldRequiredException;

@ControllerAdvice
public class controllerAdvisor extends ResponseEntityExceptionHandler {
	
	// Xử lý số nguyên chia cho 0
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<Object> handleArithmeticException(ArithmeticException ex, WebRequest request) {
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		errorResponseDTO.setErrorName(ex.getMessage());
		List<String> details = new ArrayList<>();
		details.add("Integer cannot be divied by zero!");
		errorResponseDTO.setDetail(details);
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR); // Lỗi 500 server trả ra bị sai
	}
	
	// Xử lý khi gửi data là null
	@ExceptionHandler(FieldRequiredException.class)
	public ResponseEntity<Object> handleFieldRequiredException(FieldRequiredException ex, WebRequest request) {
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		errorResponseDTO.setErrorName(ex.getMessage());
		List<String> details = new ArrayList<>();
		details.add("building name or aria is null!");
		errorResponseDTO.setDetail(details);
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_GATEWAY); // trả ra 502 khi server nhận về dữ liệu null
	}
	
	// Xử lý khi gặp vòng lặp vô hạn 
	@ExceptionHandler(OutOfMemoryError.class)
	public ResponseEntity<Object> handleOutOfMemoryError(OutOfMemoryError ex, WebRequest request) {
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		errorResponseDTO.setErrorName(ex.getMessage());
		List<String> details = new ArrayList<>();
		details.add("gặp vòng lặp vô hạn!");
		errorResponseDTO.setDetail(details);
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.LOOP_DETECTED);
	}
}

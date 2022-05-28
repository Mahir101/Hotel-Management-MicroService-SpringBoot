package com.epam.hotel.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class HotelExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public  ResponseEntity<Object> handleAllException(Exception ex,
			WebRequest request) {

		return new ResponseEntity<>( 
				new ExceptionResponse(new Date(),ex.getMessage(),
						request.getDescription(false)
						), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HotelNotFoundException.class)
	public  ResponseEntity<Object> handleHotelNotFoundException(Exception ex,
			WebRequest request) {

		return new ResponseEntity<>( 
				new ExceptionResponse(new Date(),ex.getMessage(),
						request.getDescription(false)
						), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

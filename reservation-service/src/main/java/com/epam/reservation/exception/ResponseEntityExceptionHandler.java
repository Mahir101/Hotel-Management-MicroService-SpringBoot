package com.epam.reservation.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {

		return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false)),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ReservationNotFoundException.class)
	public ResponseEntity<Object> handleReservationNotFoundException(Exception ex, WebRequest request) {

		return new ResponseEntity<>(new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false)),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

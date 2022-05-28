package com.epam.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class HotelNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public HotelNotFoundException(String message) {
		super(message);
	}

}

package com.epam.guest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class GuestNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GuestNotFoundException(String message) {
		super(message);
	}

}

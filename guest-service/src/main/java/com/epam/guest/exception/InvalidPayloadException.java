package com.epam.guest.exception;

public class InvalidPayloadException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidPayloadException(String message) {
		super(message);
	}

}

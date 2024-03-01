package com.cg.hims.Exceptions;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException {

	private HttpStatus httpStatus;
	private String message;

	public InvalidCredentialsException(HttpStatus httpStatus, String message) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
	}

	@Override
	public String toString() {
		return "InvalidCredentialsException [httpStatus=" + httpStatus + ", message=" + message + "]";
	}

}

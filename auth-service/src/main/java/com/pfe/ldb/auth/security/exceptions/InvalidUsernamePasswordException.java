package com.pfe.ldb.auth.security.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidUsernamePasswordException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private final HttpStatus httpStatus;
	
	
	public InvalidUsernamePasswordException(final String message, final HttpStatus httpStatus) {
		super(message);
		
		this.httpStatus = httpStatus;
	}


	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}
}

package com.group_3.cozyHaven.exception;

public class InvalidIdException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String message;

	public InvalidIdException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}

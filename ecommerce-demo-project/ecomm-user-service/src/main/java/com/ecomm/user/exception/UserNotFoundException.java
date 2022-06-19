package com.ecomm.user.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5956439145008272228L;

	public UserNotFoundException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

	public UserNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}

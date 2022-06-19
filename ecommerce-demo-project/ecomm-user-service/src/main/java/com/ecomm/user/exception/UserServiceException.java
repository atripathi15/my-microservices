package com.ecomm.user.exception;

public class UserServiceException extends Exception {

	private static final long serialVersionUID = 5956439145008272228L;
	
	public UserServiceException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

	public UserServiceException(String errorMessage) {
		super(errorMessage);
	}

}

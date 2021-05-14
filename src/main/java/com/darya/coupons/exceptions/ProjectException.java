package com.darya.coupons.exceptions;

import com.darya.coupons.enums.ErrorType;

public class ProjectException extends Exception {


	private ErrorType errorType;


	public ProjectException() {
	}

	public ProjectException(ErrorType errorType, String message) {
		super(message);
		this.errorType = errorType;
	}

	public ProjectException(ErrorType errorType, String message, Exception e) {
		super(message, e);
		this.errorType = errorType;
	}

	public ErrorType getErrorType() {
		return errorType;
	}
}

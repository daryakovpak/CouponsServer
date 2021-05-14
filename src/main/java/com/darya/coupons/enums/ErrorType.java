package com.darya.coupons.enums;

public enum ErrorType {

	GENERAL_ERROR(601, "General error", true), 
	LOGIN_FAILED(602, "Login failed. Please try again", false),
	NAME_ALREADY_EXISTS(603, "The name you chose already exists. Please enter another name", false), 
	MUST_ENTER_NAME(604, "Can not insert an null/empty name", false),
	MUST_ENTER_ADDRESS(605, "Can not insert an null/empty address", false),
	ID_DOES_NOT_EXIST(606, "This ID does'nt exist", false),
	INVALID_PHONENUMBER(607, "Phone number must contain digits only", false),
	INVALID_PASSWORD(608, "Password must contain 6-10 characters", false),
	NOT_ENOUGH_COUPONS_LEFT(609, "Not enough coupons left to purchase the amount requested", false),
	COUPON_EXPIERED(610, "The coupon is expiered", false),
	INVALID_PRICE(612, "Coupon price must be more than 0", false),
	INVALID_AMOUNT(613, "Coupon's amount must be more than 0", false), 
	INVALID_DATES(614, "The dates you've entered are wrong", false),
	MUST_INSERT_A_VALUE(615, "Must insert a value", false),
	INVALID_LOGIN_DETAILS(616, "Invalid login details", false),
	NO_RIGHTS(617, "You haven`t rights to do this action", false),
	HACKING_ATTEMP(618, "You haven`t rights to do this action", true);

	private int errorNumber;
	private String errorMessage;
	private boolean isShowStackTrace;

	private ErrorType(int errorNumber, String internalMessage, boolean isShowStackTrace) {
		this.errorNumber = errorNumber;
		this.errorMessage = internalMessage;
		this.isShowStackTrace = isShowStackTrace;
	}

	private ErrorType(int errorNumber, String internalMessage) {
		this.errorNumber = errorNumber;
		this.errorMessage = internalMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public boolean isShowStackTrace() {
		return isShowStackTrace;
	}

	public int getErrorNumber() {
		return errorNumber;
	}
}

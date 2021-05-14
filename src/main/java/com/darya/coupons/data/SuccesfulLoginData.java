package com.darya.coupons.data;

import com.darya.coupons.enums.UserType;

public class SuccesfulLoginData {
	
	private int token;
	private UserType userType;
	
	public SuccesfulLoginData() {
	}

	public SuccesfulLoginData(int token, UserType userType) {
		this.token = token;
		this.userType = userType;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}


}

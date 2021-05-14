package com.darya.coupons.data;

public class UserLoginDetails {

	private String userName;
	private String password;
	
	public UserLoginDetails() {
	}

	public UserLoginDetails(String username, String password) {
		super();
		this.userName = username;
		this.password = password;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserLoginDetails [userName=" + userName + ", password=" + password + "]";
	}
	
	
}

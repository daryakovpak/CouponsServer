package com.darya.coupons.data;

import com.darya.coupons.entities.UserEntity;
import com.darya.coupons.enums.UserType;

public class UserLoginData {

	private long userId;
	private Long companyId;
	private UserType userType;

	public UserLoginData(UserEntity userEntity) {
		this.userId = userEntity.getId();
		if (!(userEntity.getCompany() == null)) {
			this.companyId = userEntity.getCompany().getId();
		}
		this.userType = userEntity.getUserType();
	}

	public UserLoginData(long userId, Long companyId, UserType userType) {
		this.userId = userId;
		this.companyId = companyId;
		this.userType = userType;
	}

	public UserLoginData() {
	}

	public UserLoginData(long userId, UserType userType) {
		super();
		this.userId = userId;
		this.userType = userType;
	}

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}


}

package com.darya.coupons.dto;

import com.darya.coupons.entities.CompanyEntity;
import com.darya.coupons.exceptions.ProjectException;


public class CompanyDto {

	private long id;
	private String name;
	private String address;
	private String phoneNumber;


	public CompanyDto() {
	}

	public CompanyDto(CompanyEntity companyEntity) throws ProjectException {
		this.id = companyEntity.getId();
		this.name = companyEntity.getName();
		this.address = companyEntity.getAddress();
		this.phoneNumber = companyEntity.getPhoneNumber();
	}

	public CompanyDto(String name, String address, String phoneNumber) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public CompanyDto(long id, String name, String address, String phoneNumber) {
		this(name, address, phoneNumber);
		this.id = id;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}

}

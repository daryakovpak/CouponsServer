package com.darya.coupons.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.darya.coupons.dto.CompanyDto;

@Entity
@Table(name = "Companies")
public class CompanyEntity {

	@Id
	@GeneratedValue
	@Column(name = "Id")
	private Long id;

	@Column(name = "Name", unique = true, nullable = false)
	private String name;

	@Column(name = "Address", nullable = false)
	private String address;

	@Column(name = "Phonenumber", nullable = false)
	private String phoneNumber;

	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
	private List<UserEntity> users;

	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
	private List<CouponEntity> coupons;

	public CompanyEntity(Long id, String name, String address, String phoneNumber, List<UserEntity> users,
			List<CouponEntity> coupons) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.users = users;
		this.coupons = coupons;
	}

	public CompanyEntity(CompanyDto companyDto) {
		this.id = companyDto.getId();
		this.name = companyDto.getName();
		this.address = companyDto.getAddress();
		this.phoneNumber = companyDto.getPhoneNumber();
	}

	public CompanyEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public List<CouponEntity> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<CouponEntity> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber
				+ ", users=" + users + ", coupons=" + coupons + "]";
	}

}

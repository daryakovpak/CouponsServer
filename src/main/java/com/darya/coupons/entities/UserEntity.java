package com.darya.coupons.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.darya.coupons.dto.UserDto;
import com.darya.coupons.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "Users")

public class UserEntity  {

	@Id
	@GeneratedValue
	@Column (name = "id")
	private long id;

	@Column(name = "Username", unique = true, nullable = false)
	private String username;

	@Column(name = "Password", nullable = false)
	private String password;

	@Column(name = "Usertype", nullable = false)
	private UserType userType;

	@Column(name = "First_name", nullable = false)
	private String firstName;

	@Column(name = "Last_name", nullable = false)
	private String lastName;

	@ManyToOne
	@JsonIgnore
	private CompanyEntity company;

	@OneToMany (mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<PurchaseEntity> purchases;

	public UserEntity(long id, String username, String password, UserType userType, String firstName,
			String lastName, CompanyEntity company, List<PurchaseEntity> purchases) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
		this.purchases = purchases;
	}

	public UserEntity (UserDto userDto) {
		this.id = userDto.getId();
		this.username = userDto.getUserName();
		this.password = userDto.getPassword();
		this.userType = userDto.getUserType();
		if (this.userType.equals(UserType.COMPANY)) {
			this.company.setId(userDto.getCompanyId());
		}
		this.firstName = userDto.getFirstName();
		this.lastName = userDto.getLastName();
	} 
	public UserEntity() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

	public List<PurchaseEntity> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<PurchaseEntity> purchases) {
		this.purchases = purchases;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password +  ", userType=" + userType + ", firstName=" + firstName + ", lastName=" + lastName + ", company="
				+ company + ", purchases=" + purchases + "]";
	}



}

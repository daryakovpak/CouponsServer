package com.darya.coupons.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table (name = "Purchases")
public class PurchaseEntity  {

	@Id
	@GeneratedValue
	@Column (name = "id")
	private long id;

	@Column (name = "Amount", nullable = false)
	private int amount;

	@Column (name = "Timestamp", nullable = false)
	private Date timestamp;

	@ManyToOne
	@JsonIgnore
	private CouponEntity coupon;

	@ManyToOne
	@JsonIgnore
	private UserEntity user;

	public PurchaseEntity(long id, int amount, Date timestamp, CouponEntity coupon, UserEntity user) {
		super();
		this.id = id;
		this.amount = amount;
		this.timestamp = timestamp;
		this.coupon = coupon;
		this.user = user;
	}

	public PurchaseEntity() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public CouponEntity getCoupon() {
		return coupon;
	}

	public void setCoupon(CouponEntity coupon) {
		this.coupon = coupon;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", amount=" + amount
				+ ", timestamp=" + timestamp + ", coupon=" + coupon + ", user=" + user + "]";
	}



}

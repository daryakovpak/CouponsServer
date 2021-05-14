package com.darya.coupons.dto;

import java.sql.Date;

import com.darya.coupons.entities.PurchaseEntity;

public class PurchaseToUser {

	private long id;
	private String couponTitle;
	private int amount;
	private double price;
	private Date timestamp;

	public PurchaseToUser (PurchaseEntity purchaseEntity, String title, double price) {
		this.id = purchaseEntity.getId();
		this.amount = purchaseEntity.getAmount();
		this.timestamp = purchaseEntity.getTimestamp();
		this.couponTitle = title;
		this.price = price;
	}
	public PurchaseToUser (PurchaseEntity purchaseEntity) {
		this.id = purchaseEntity.getId();
		this.amount = purchaseEntity.getAmount();
		this.timestamp = purchaseEntity.getTimestamp();
	}

	public PurchaseToUser(long id, String couponTitle, int amount, Date timestamp, double price) {
		super();
		this.id = id;
		this.couponTitle = couponTitle;
		this.amount = amount;
		this.timestamp = timestamp;
		this.price = price;
	}
	public PurchaseToUser() {
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCouponTitle() {
		return couponTitle;
	}
	public void setCouponTitle(String couponTitle) {
		this.couponTitle = couponTitle;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "PurchaseToUser [id=" + id + ", couponTitle=" + couponTitle + ", amount=" + amount + ", price=" + price
				+ ", timestamp=" + timestamp + "]";
	}





}

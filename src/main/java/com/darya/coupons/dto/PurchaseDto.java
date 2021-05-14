package com.darya.coupons.dto;

import java.sql.Date;

import com.darya.coupons.entities.PurchaseEntity;
import com.darya.coupons.enums.ErrorType;
import com.darya.coupons.exceptions.ProjectException;

public class PurchaseDto {

	private long id;
	private long userId;
	private long couponId;
	private int amount;
	private Date timestamp;

	public PurchaseDto() {
	}

	public PurchaseDto(PurchaseEntity purchaseEntity) throws ProjectException {
		try {
			this.id = purchaseEntity.getId();
			this.userId = purchaseEntity.getUser().getId();
			this.couponId = purchaseEntity.getCoupon().getId();
			this.amount = purchaseEntity.getAmount();
			this.timestamp = purchaseEntity.getTimestamp();
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "PurchaseDto(PurchaseEntity purchaseEntity) faileb by "+ e.getMessage());
		}
	}
	public PurchaseDto(long userId, long couponId, int amount, Date timestamp) {
		this.userId = userId;
		this.couponId = couponId;
		this.amount = amount;
		this.timestamp = timestamp;
	}
	public PurchaseDto(long id, long userId, long couponId, int amount, Date timestamp) {
		this(userId, couponId, amount, timestamp);
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
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

	@Override
	public String toString() {
		return "Purchases [id=" + id + ", userId=" + userId + ", couponId=" + couponId + ", amount=" + amount
				+ ", timestamp=" + timestamp + "]";
	}


}

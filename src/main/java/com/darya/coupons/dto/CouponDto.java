package com.darya.coupons.dto;

import java.sql.Date;

import com.darya.coupons.entities.CouponEntity;
import com.darya.coupons.enums.CategoryType;
import com.darya.coupons.enums.ErrorType;
import com.darya.coupons.exceptions.ProjectException;

public class CouponDto {

	private long id;
	private long companyId;
	private CategoryType category;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private double price;
	private int amount;


	public CouponDto(CouponEntity couponEntity) throws ProjectException{
		try {
			this.id = couponEntity.getId();
			this.companyId = couponEntity.getCompany().getId();
			this.category = couponEntity.getCategory();
			this.title = couponEntity.getTitle();
			this.description = couponEntity.getDescription();
			this.startDate = couponEntity.getStartDate();
			this.endDate = couponEntity.getEndDate();
			this.price = couponEntity.getPrice();
			this.amount = couponEntity.getAmount();
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "CouponDto(CouponEntity couponEntity) faileb by "+ e.getMessage());
		}		
	}

	public CouponDto(int companyId, CategoryType category, String title, String description, Date startDate, Date endDate,
			double price, int amount) {
		this.companyId = companyId;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.amount = amount;
	}

	public CouponDto(long id, int companyId, CategoryType category, String title, String description, Date startDate,
			Date endDate, double price, int amount) {
		this(companyId, category, title, description,startDate, endDate, price, amount);
		this.id = id;
	}

	public CouponDto() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long id) {
		this.companyId = id;
	}

	public CategoryType getCategory() {
		return category;
	}

	public void setCategory(CategoryType category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Coupons [id=" + id + ", companyId=" + companyId + ", category=" + category + ", title=" + title
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", price="
				+ price + ", amount=" + amount + "]";
	}


}

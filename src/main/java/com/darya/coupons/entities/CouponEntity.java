package com.darya.coupons.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.darya.coupons.dto.CouponDto;
import com.darya.coupons.enums.CategoryType;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table (name = "Coupons")
public class CouponEntity  {

	@Id
	@GeneratedValue
	@Column (name = "id")
	private long id;

	@Column (name = "Category", unique = false, nullable = false)
	private CategoryType category;

	@Column (name = "Title", unique = true, nullable = false)
	private String title;

	@Column (name = "Description", unique = false, nullable = false)
	private String description;

	@Column (name = "Start_date", unique = false, nullable = false)
	private Date startDate;

	@Column (name = "End_date", unique = false, nullable = false)
	private Date endDate;

	@Column (name = "Price", unique = false, nullable = false)
	private double price;

	@Column (name = "Amount", unique = false, nullable = false)
	private int amount;

	@ManyToOne
	@JsonIgnore
	private CompanyEntity company;

	@OneToMany (mappedBy = "coupon", cascade = CascadeType.REMOVE)
	private List<PurchaseEntity> purchases;

	public CouponEntity(long id, CategoryType category, String title, String description, Date startDate,
			Date endDate, double price, int amount, CompanyEntity company, List<PurchaseEntity> purchases) {
		super();
		this.id = id;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.amount = amount;
		this.company = company;
		this.purchases = purchases;
	}

	public CouponEntity (CouponDto couponDto, CompanyEntity companyEntity) {
		this.category = couponDto.getCategory();
		this.title = couponDto.getTitle();
		this.description = couponDto.getDescription();
		this.startDate = couponDto.getStartDate();
		this.endDate = couponDto.getEndDate();
		this.price = couponDto.getPrice();
		this.amount = couponDto.getAmount();
		this.company = companyEntity;
	}

	public CouponEntity (CouponDto couponDto) {
		this.category = couponDto.getCategory();
		this.title = couponDto.getTitle();
		this.description = couponDto.getDescription();
		this.startDate = couponDto.getStartDate();
		this.endDate = couponDto.getEndDate();
		this.price = couponDto.getPrice();
		this.amount = couponDto.getAmount();
	}
	public CouponEntity() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		return "Coupon [id=" + id + /*", companyId=" + companyId +*/ ", category=" + category + ", title=" + title
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", price="
				+ price + ", amount=" + amount + ", company=" + company + ", purchases=" + purchases + "]";
	}


}

package com.darya.coupons.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.darya.coupons.dao.ICouponDao;
import com.darya.coupons.dto.CouponDto;
import com.darya.coupons.entities.CompanyEntity;
import com.darya.coupons.entities.CouponEntity;
import com.darya.coupons.enums.CategoryType;
import com.darya.coupons.enums.ErrorType;
import com.darya.coupons.exceptions.ProjectException;
@Controller
public class CouponsController {

	@Autowired
	private ICouponDao couponDao;

	@Autowired
	private CompaniesController controller;

	public CouponsController() {
	}

	public void createCoupon (CouponDto coupon) throws ProjectException {
		validateCouponInfo(coupon);
		try {
			long companyId = coupon.getCompanyId();
			CompanyEntity company = controller.getCompanyEntity(companyId);
			CouponEntity entity = new CouponEntity(coupon, company);
			this.couponDao.save(entity);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "CreateCoupon failed on ", e);
		}
	}

	public void deleteCoupon (long id) throws ProjectException {
		try {
			this.couponDao.deleteById(id);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "deleteCoupon failed on ", e);
		}
	}

	public CouponDto getCouponById (long id) throws ProjectException {
		try {
			return this.couponDao.getCouponById(id);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "getCouponById failed on ", e);
		}
	}

	public List<CouponDto> getCouponsByCompanyId (long companyId) throws ProjectException {
		try {
			return this.couponDao.getCouponsByCompanyId(companyId);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "getCouponsByCompanyId failed on ", e);		
		}
	}

	public void updateCoupon (CouponDto coupon) throws ProjectException {
		validateCouponInfo(coupon);
		CouponEntity couponEntity = new CouponEntity(coupon);
		try {
			this.couponDao.save(couponEntity);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "updateCoupon failed on ", e);	
		}
	}

	public  List <CouponDto> getCouponByType(CategoryType category) throws ProjectException {
		try {
			return this.couponDao.getCouponsByType(category);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "getCouponByType failed on ", e);	
		}
	}

	public  List <CouponDto> getAllCoupons() throws ProjectException {
		try {
			return this.couponDao.getAllCoupons();
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "getAllCoupons failed on ", e);	
		}
	}

	public  List <CouponDto> getPurchasedCouponsByMaxPrice (double maxPrice, long userId) throws ProjectException {
		try {
			return this.couponDao.getPurchasedCouponsByMaxPrice(maxPrice, userId);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "getPurchasedCouponsByMaxPrice failed on ", e);	
		}
	}

	public CouponEntity getCouponEntity(long couponId) throws ProjectException {
		try {
			return couponDao.findById(couponId).get();
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "getCouponEntity failed on ", e);
		}		
	}

	private void validateCouponInfo (CouponDto coupon) throws ProjectException {
		Date now = new Date(System.currentTimeMillis());
		if (coupon.getTitle() == null) {
			throw new ProjectException(ErrorType.MUST_ENTER_NAME, "Null coupon title");
		}
		if (coupon.getTitle().isEmpty()) {
			throw new ProjectException(ErrorType.MUST_ENTER_NAME, "Coupon title can not be empty");
		}		
		if (coupon.getTitle().length() < 5) {
			throw new ProjectException(ErrorType.MUST_ENTER_NAME, "Coupon title is too short");
		} 
		if (coupon.getDescription() == null) {
			throw new ProjectException(ErrorType.MUST_INSERT_A_VALUE, "Null coupon description");
		}

		if (coupon.getDescription().isEmpty()) {
			throw new ProjectException(ErrorType.MUST_INSERT_A_VALUE, "Coupon description can not be empty");
		}		
		if (coupon.getDescription().length() < 10) {
			throw new ProjectException(ErrorType.MUST_INSERT_A_VALUE, "Coupon description is too short");
		} 
		if (coupon.getEndDate().before(coupon.getStartDate())) {
			throw new ProjectException(ErrorType.INVALID_DATES, "Coupon start and end dates are not correct");
		}
		if (coupon.getEndDate().before(now)) {
			throw new ProjectException(ErrorType.INVALID_DATES, "Coupon end date is not correct");
		}
		if (coupon.getPrice() < 0) {
			throw new ProjectException(ErrorType.INVALID_PRICE, "Coupon price must be more than 0");
		}
		if (coupon.getAmount() < 1) {
			throw new ProjectException(ErrorType.INVALID_AMOUNT, "Coupon amount must be more than 0");
		}
	}
}

package com.darya.coupons.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.darya.coupons.exceptions.ProjectException;
import com.darya.coupons.dao.ICouponDao;
import com.darya.coupons.dao.IPurchaseDao;
import com.darya.coupons.data.UserLoginData;
import com.darya.coupons.dto.CouponDto;
import com.darya.coupons.dto.PurchaseDto;
import com.darya.coupons.dto.PurchaseToUser;
import com.darya.coupons.entities.PurchaseEntity;
import com.darya.coupons.enums.ErrorType;
import com.darya.coupons.enums.UserType;
@Controller
public class PurchasesController {

	@Autowired
	private IPurchaseDao dao;

	public PurchasesController() {
	}

	@Autowired
	CouponsController couponsController;

	@Autowired
	UsersController usersController;

	@Autowired
	ICouponDao couponDao;

	public void createPurchase (PurchaseDto purchase) throws ProjectException {
		validateCreatePurchase(purchase);
		try {
			long couponId = purchase.getCouponId();
			com.darya.coupons.entities.CouponEntity coupon = couponsController.getCouponEntity(couponId);
			long userId = purchase.getUserId();
			com.darya.coupons.entities.UserEntity user = usersController.getUserEntity(userId);
			PurchaseEntity entity = new PurchaseEntity();
			entity.setAmount(purchase.getAmount());
			entity.setTimestamp(purchase.getTimestamp());
			entity.setCoupon(coupon);
			entity.setUser(user);
			coupon.setAmount(coupon.getAmount()-purchase.getAmount());
			couponDao.save(coupon);
			this.dao.save(entity);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "CreatePurchase failed on ", e);
		}
	}

	public void deletePurchase (long id) throws ProjectException {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "deletePurchase failed on ", e);
		}
	}

	public  List <PurchaseDto> getAllPurchases() throws ProjectException {
		try {
			return this.dao.getAllPurchases();
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "getAllPurchases failed on ", e);
		}
	}

	public PurchaseDto getPurchase (long id, UserLoginData data) throws ProjectException {
		try {
			if (data.getUserType().equals(UserType.ADMIN)) {
				return this.dao.getPurchaseById(id);
			}
			if (data.getUserType().equals(UserType.COMPANY)) {
				return this.dao.getPurchasesByIdForCompany(id,data.getCompanyId());
			}
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "getPurchasesByUserId failed: ", e);
		}
		throw new ProjectException(ErrorType.HACKING_ATTEMP, "Hacking attemp by " + data.getUserId());
	}

	public  List <PurchaseDto> getPurchasesByUserId (long userId, UserLoginData data) throws ProjectException {
		try {
			if (data.getUserType().equals(UserType.ADMIN)) {
				return this.dao.getPurchasesByUserId(userId);
			}
			if (data.getUserType().equals(UserType.COMPANY)) {
				return this.dao.getPurchasesByUserIdForCompany(userId,data.getCompanyId());
			}
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "getPurchasesByUserId failed: ", e);
		}
		throw new ProjectException(ErrorType.HACKING_ATTEMP, "Hacking attemp by " + data.getUserId());
	}

	public  List <PurchaseToUser> getPurchasesForUser (long userId) throws ProjectException {
		try {
			return this.dao.getPurchasesForUser(userId);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "getPurchasesForUser failed: ", e);
		}
	}

	public PurchaseToUser getPurchaseForUser (long purchaseId, long userId) throws ProjectException {
		try {
			return this.dao.getPurchaseForUser(purchaseId, userId);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "getPurchaseForUser failed: ", e);
		}
	}

	public  List <PurchaseDto> getPurchasesByCouponId (long couponId, UserLoginData data) throws ProjectException {
		try {
			if (data.getUserType().equals(UserType.ADMIN)) {
				return this.dao.getPurchasesByCouponId(couponId);
			}
			if (data.getUserType().equals(UserType.COMPANY)) {
				return this.dao.getPurchasesByCouponIdForCompany(couponId,data.getCompanyId());
			}
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "getPurchasesByCouponId failed: ", e);
		}
		throw new ProjectException(ErrorType.HACKING_ATTEMP, "Hacking attemp by " + data.getUserId());
	}

	private void validateCreatePurchase(PurchaseDto purchase) throws ProjectException {
		//		We don`t check user_id because create purchase we can only after 
		//		successful login
		if (purchase.getAmount() < 1) {
			throw new ProjectException(ErrorType.INVALID_AMOUNT, "Coupon amount must be more than 0");
		}
		CouponDto coupon = couponsController.getCouponById(purchase.getCouponId());
		if (coupon == null) {
			throw new ProjectException(ErrorType.ID_DOES_NOT_EXIST, "Coupon not found");
		}
		Date endDate = coupon.getEndDate();
		if (endDate.before(purchase.getTimestamp())) {
			throw new ProjectException(ErrorType.COUPON_EXPIERED, "The coupon is expiered");
		}
		if ((coupon.getAmount()-purchase.getAmount()) < 0) {
			throw new ProjectException(ErrorType.NOT_ENOUGH_COUPONS_LEFT, 
					"Not enough coupons left to purchase the amount requested");
		}
	}
}

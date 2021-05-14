package com.darya.coupons.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.darya.coupons.controllers.CouponsController;
import com.darya.coupons.data.UserLoginData;
import com.darya.coupons.dto.CouponDto;
import com.darya.coupons.enums.CategoryType;
import com.darya.coupons.exceptions.ProjectException;

@RestController
@RequestMapping ("/coupons")

public class CouponsApi {

	@Autowired
	CouponsController couponsController;

	//	functions that only the company has access to
	@PostMapping
	public void createCoupon(@RequestBody CouponDto coupon, HttpServletRequest request) throws ProjectException {
		UserLoginData data = (UserLoginData) request.getAttribute("userLoginData");
		coupon.setCompanyId(data.getCompanyId());
		couponsController.createCoupon(coupon);
	}

	@PutMapping ("/{couponId}")
	public void updateCoupon(@PathVariable("couponId") long couponId ,@RequestBody CouponDto coupon) throws ProjectException {
		//	we'll check who wants to update 
		coupon.setId(couponId);
		couponsController.updateCoupon(coupon);
	}

	@DeleteMapping ("/{couponId}")
	public void deleteCoupon(@PathVariable("couponId") long id) throws ProjectException {
		couponsController.deleteCoupon(id);
	}

	@GetMapping ("/byCategory")
	public  List <CouponDto> getCouponsByCategory (@RequestParam ("category") CategoryType category) throws ProjectException {
		return couponsController.getCouponByType(category);
	}

	@GetMapping("/{couponId}")
	public  CouponDto getCouponById (@PathVariable ("couponId") long id) throws ProjectException {
		return couponsController.getCouponById(id);
	}

	@GetMapping
	public  List <CouponDto> getAllCoupons() throws ProjectException {
		return couponsController.getAllCoupons();
	}

	//	get coupons by company - add!
	@GetMapping ("/byCompanyId")
	public List <CouponDto> getCouponsByCompanyId (@RequestParam ("companyId") Long companyId) throws ProjectException {
		return couponsController.getCouponsByCompanyId(companyId);
	}

	@GetMapping ("/byMaxPrice")
	public List<CouponDto>  getPurchasedCouponsByMaxPrice (@RequestParam ("maxPrice") double maxPrice, HttpServletRequest request) throws ProjectException{
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		long userId = userLoginData.getUserId();
		return couponsController.getPurchasedCouponsByMaxPrice(maxPrice, userId);
	}


}



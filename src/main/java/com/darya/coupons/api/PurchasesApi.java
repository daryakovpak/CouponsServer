package com.darya.coupons.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.darya.coupons.controllers.PurchasesController;
import com.darya.coupons.data.UserLoginData;
import com.darya.coupons.dto.PurchaseDto;
import com.darya.coupons.dto.PurchaseToUser;
import com.darya.coupons.exceptions.ProjectException;

@RestController
@RequestMapping ("/purchases")

public class PurchasesApi {

	@Autowired
	PurchasesController purchasesController;

	//	for customers:
	@PostMapping
	public void createPurchase (@RequestBody PurchaseDto purchase, HttpServletRequest request) throws ProjectException {
		UserLoginData data = (UserLoginData) request.getAttribute("userLoginData");
		purchase.setUserId(data.getUserId());
		this.purchasesController.createPurchase(purchase);
	}

	@GetMapping("/allMyPurchases")
	public  List<PurchaseToUser> getAllMyPurchases (HttpServletRequest request) throws ProjectException {
		UserLoginData data = (UserLoginData) request.getAttribute("userLoginData");
		return this.purchasesController.getPurchasesForUser(data.getUserId());
	}

	@GetMapping ("/toUser/{purchaseId}")
	public PurchaseToUser getPurchaseForUser (@PathVariable ("purchaseId") long purchaseId, HttpServletRequest request) throws ProjectException {
		UserLoginData data = (UserLoginData) request.getAttribute("userLoginData");
		return this.purchasesController.getPurchaseForUser(purchaseId, data.getUserId());
	}

	//	for admin only

	@DeleteMapping ("/{purchaseId}")
	public void deletePurchase (@PathVariable ("purchaseId") long id) throws ProjectException {
		this.purchasesController.deletePurchase(id);
	}

	@GetMapping 
	public  List <PurchaseDto> getAllPurchases () throws ProjectException {
		return this.purchasesController.getAllPurchases();
	}

	//	for company and/or admin
	@GetMapping("/byUserId")
	public  List <PurchaseDto> getPurchasesByUserId (@RequestParam ("couponId") long userId, HttpServletRequest request) throws ProjectException {
		UserLoginData data = (UserLoginData) request.getAttribute("userLoginData");
		return this.purchasesController.getPurchasesByUserId(userId, data);
	}

	@GetMapping ("/byCouponId")
	public  List <PurchaseDto> getPurchasesByCouponId (@RequestParam ("couponId") long couponId, HttpServletRequest request) throws ProjectException {
		UserLoginData data = (UserLoginData) request.getAttribute("userLoginData");
		return this.purchasesController.getPurchasesByCouponId(couponId, data);
	}

	@GetMapping ("/{purchaseId}")
	public PurchaseDto getPurchase (@PathVariable ("purchaseId") long id, HttpServletRequest request) throws ProjectException {
		UserLoginData data = (UserLoginData) request.getAttribute("userLoginData");
		return this.purchasesController.getPurchase(id, data);
	}

}

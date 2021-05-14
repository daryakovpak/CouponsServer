package com.darya.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darya.coupons.dto.PurchaseDto;
import com.darya.coupons.dto.PurchaseToUser;
import com.darya.coupons.entities.PurchaseEntity;

@Repository
public interface IPurchaseDao extends CrudRepository<PurchaseEntity, Long> {

	@Query("select new com.darya.coupons.dto.PurchaseDto(p) from PurchaseEntity p where p.id = ?1")
	public PurchaseDto getPurchaseById (long id);

	@Query("select new com.darya.coupons.dto.PurchaseDto(p) from PurchaseEntity p")
	public  List<PurchaseDto>  getAllPurchases();

	@Query("select new com.darya.coupons.dto.PurchaseDto(p) from PurchaseEntity p where p.user.id = ?1")
	public  List <PurchaseDto> getPurchasesByUserId (long userId);
	
	@Query("select new com.darya.coupons.dto.PurchaseToUser(p, c.title, c.price) from PurchaseEntity p join CouponEntity c "
			+ "on p.coupon.id = c.id where p.user.id = ?1")
	public  List <PurchaseToUser> getPurchasesForUser (long userId);
	
	@Query("select new com.darya.coupons.dto.PurchaseToUser(p, c.title, c.price) from PurchaseEntity p join CouponEntity c "
			+ "on p.coupon.id = c.id where p.id = ?1 and p.user.id = ?2")
	public PurchaseToUser getPurchaseForUser (long purchaseId, long userId);
	
	@Query("select new com.darya.coupons.dto.PurchaseDto(p) from PurchaseEntity p where p.user.id = ?1 and p.coupon.company.id = ?2")
	public List<PurchaseDto> getPurchasesByUserIdForCompany(long userId, Long companyId);

	@Query("select new com.darya.coupons.dto.PurchaseDto(p) from PurchaseEntity p where p.coupon.id = ?1")
	public  List <PurchaseDto> getPurchasesByCouponId (long couponId);
	
	@Query("select new com.darya.coupons.dto.PurchaseDto(p) from PurchaseEntity p where p.coupon.id = ?1 and p.coupon.company.id = ?2")
	public List<PurchaseDto> getPurchasesByCouponIdForCompany(long couponId, Long companyId);

	@Query("select new com.darya.coupons.dto.PurchaseDto(p) from PurchaseEntity p where p.id = ?1 and p.coupon.company.id = ?2")
	public PurchaseDto getPurchasesByIdForCompany(long id, Long companyId);

}

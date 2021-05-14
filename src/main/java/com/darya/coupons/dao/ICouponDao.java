package com.darya.coupons.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darya.coupons.dto.CouponDto;
import com.darya.coupons.entities.CouponEntity;
import com.darya.coupons.enums.CategoryType;

@Repository
public interface ICouponDao extends CrudRepository<CouponEntity, Long>{

	@Query("select new com.darya.coupons.dto.CouponDto(c) from CouponEntity c where c.company.id = ?1" )
	public  List<CouponDto> getCouponsByCompanyId (long companyId);

	@Query("select new com.darya.coupons.dto.CouponDto(c) from CouponEntity c where c.price <= ?1"
			+ " and c.id in (select p.coupon.id from PurchaseEntity p where p.user.id = ?2)")
	public  List <CouponDto> getPurchasedCouponsByMaxPrice (double maxPrice, long userId);

	@Query("select new com.darya.coupons.dto.CouponDto(c) from CouponEntity c where c.category = ?1")
	public  List<CouponDto> getCouponsByType (CategoryType category);

	@Query("select new com.darya.coupons.dto.CouponDto(c) from CouponEntity c where c.id =?1")
	public CouponDto getCouponById (long id);

	@Query("select new com.darya.coupons.dto.CouponDto(c) from CouponEntity c")
	public  List <CouponDto> getAllCoupons();

	public void deleteAllByEndDateBefore(Date endDate);

}

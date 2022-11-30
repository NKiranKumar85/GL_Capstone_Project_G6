package com.gl.capstone.DiscountCouponsService.repositroy;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.gl.capstone.DiscountCouponsService.model.DiscountCoupon;
import com.gl.capstone.DiscountCouponsService.model.UserDiscountCoupons;

public interface UserDiscountCouponRepository extends JpaRepository<UserDiscountCoupons, Long>{
	
	@Query("select udc from UserDiscountCoupons udc "
			+ "where udc.user.userid = ?1 and udc.discountCoupon.couponId = ?2")
	public Optional<UserDiscountCoupons> 
	findByUserAndDiscountCoupon(long userid, long discountCouponid);
	
	@Modifying
	@Transactional
	@Query("delete from UserDiscountCoupons udc where udc.user.userid = ?1")
	public void deleteAllUserCoupons(long userid);
	
	@Modifying
	@Transactional
	@Query("delete from UserDiscountCoupons udc where udc.discountCoupon.couponId = ?1")
	public void deleteByCouponType(long couponid);
	
	@Query("select udc from UserDiscountCoupons udc where udc.user.userid = ?1")
	public List<UserDiscountCoupons> getAllUserCoupons(long userid);
	
	@Query("select udc.discountCoupon from UserDiscountCoupons udc "
			+ "where udc.reedemCode = ?1")
	public Optional<DiscountCoupon> getCoupon(long redeemCode);
}

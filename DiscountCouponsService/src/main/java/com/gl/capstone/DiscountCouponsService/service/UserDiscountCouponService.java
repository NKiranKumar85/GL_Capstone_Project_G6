package com.gl.capstone.DiscountCouponsService.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.capstone.DiscountCouponsService.model.DiscountCoupon;
import com.gl.capstone.DiscountCouponsService.model.User;
import com.gl.capstone.DiscountCouponsService.model.UserDiscountCoupons;
import com.gl.capstone.DiscountCouponsService.repositroy.DiscountCouponRepositroy;
import com.gl.capstone.DiscountCouponsService.repositroy.UserDiscountCouponRepository;

@Service
public class UserDiscountCouponService {

	@Autowired
	private UserDiscountCouponRepository userDiscountCouponRepository;
	
	public String assignUserCoupon(long userid, long couponid) {
		if(userDiscountCouponRepository.findByUserAndDiscountCoupon(userid, couponid).isEmpty()) {
			User user = new User();
			user.setUserid(userid);
			DiscountCoupon coupon = new DiscountCoupon();
			coupon.setCouponId(couponid);
			long redeemCode = new Date().getTime();
			UserDiscountCoupons userDiscountCoupons = 
					new UserDiscountCoupons(redeemCode, coupon, user);
			userDiscountCouponRepository.save(userDiscountCoupons);
			return "coupon assigned";
		}else {
			return "coupon already assigned";
		}
	}
	
	public DiscountCoupon getDiscountCoupon(long redeemCode) {
		Optional<UserDiscountCoupons> coupon = userDiscountCouponRepository.findById(redeemCode);
		return coupon.isPresent() ? coupon.get().getDiscountCoupon() : null;
	}
	
	public List<UserDiscountCoupons> getUserCoupons(long userId){
		return userDiscountCouponRepository.getAllUserCoupons(userId);
	}
	
	public void deleteUserCoupons(long userId){
		userDiscountCouponRepository.deleteAllUserCoupons(userId);;
	}
	
	public void deleteRedeemedCoupon(long couponId) {
		userDiscountCouponRepository.deleteById(couponId);
	}
	
}

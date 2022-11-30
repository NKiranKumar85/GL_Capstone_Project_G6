package com.gl.capstone.DiscountCouponsService.service;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gl.capstone.DiscountCouponsService.model.DiscountCoupon;
import com.gl.capstone.DiscountCouponsService.repositroy.DiscountCouponRepositroy;
import com.gl.capstone.DiscountCouponsService.repositroy.UserDiscountCouponRepository;

@Service
public class DiscountCouponService {

	@Autowired
	private DiscountCouponRepositroy discountCouponRepositroy;
	
	@Autowired
	private UserDiscountCouponRepository userDiscountCouponRepository;

	public String addNewCoupon(DiscountCoupon coupon) {
		discountCouponRepositroy.save(coupon);
		return "new coupon added";
	}

	public String deleteCoupon(long couponId) {
		if (discountCouponRepositroy.findById(couponId).isPresent()) {
			userDiscountCouponRepository.deleteByCouponType(couponId);
			discountCouponRepositroy.deleteById(couponId);
			return "coupon deleted";
		} else {
			return "coupon not found";
		}
	}
	
	public List<DiscountCoupon> getAllCouponTypes() {
		return discountCouponRepositroy.findAll();
	}
}

package com.gl.capstone.DiscountCouponsService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl.capstone.DiscountCouponsService.model.DiscountCoupon;
import com.gl.capstone.DiscountCouponsService.model.UserDiscountCoupons;
import com.gl.capstone.DiscountCouponsService.service.DiscountCouponService;
import com.gl.capstone.DiscountCouponsService.service.UserDiscountCouponService;

@RestController
public class RestDiscountCouponController {

	@Autowired
	private UserDiscountCouponService userDiscountCouponService;

	@Autowired
	private DiscountCouponService discountCouponService;

	@GetMapping("/getUserCoupons/{userId}")
	public List<UserDiscountCoupons> getUserCoupons(@PathVariable("userId") long userId) {
		return userDiscountCouponService.getUserCoupons(userId);
	}

	@PostMapping("/admin/setUserCoupons/{couponId}/{userid}")
	public String setUserCoupons(@PathVariable("couponId") long couponId, @PathVariable("userid") long userid) {
		return userDiscountCouponService.assignUserCoupon(userid, couponId);
	}

	@DeleteMapping("/removeUserCoupon/{redeemCode}")
	public void removeUserCoupon(@PathVariable("redeemCode") long redeemCode) {
		userDiscountCouponService.deleteRedeemedCoupon(redeemCode);
	}

	@DeleteMapping("/removeAllUserCoupon/{userId}")
	public void removeAllUserCoupon(@PathVariable("userId") long userId) {
		userDiscountCouponService.deleteUserCoupons(userId);
	}

	@GetMapping("/getUserCoupon/{redeemCode}")
	public DiscountCoupon getUserCoupon(@PathVariable("redeemCode") long redeemCode) {
		return userDiscountCouponService.getDiscountCoupon(redeemCode);
	}

	@PostMapping("/admin/addNewCoupon")
	public String addNewCoupon(@RequestBody DiscountCoupon coupon) {
		return discountCouponService.addNewCoupon(coupon);
	}

	@DeleteMapping("/admin/deleteCoupon/{couponId}")
	public String deleteCoupon(@PathVariable("couponId") long couponId) {
		return discountCouponService.deleteCoupon(couponId);
	}
	
	@GetMapping("/admin/getAllCouponTypes")
	public List<DiscountCoupon> getAllCoupons(){
		return discountCouponService.getAllCouponTypes();
	}
}

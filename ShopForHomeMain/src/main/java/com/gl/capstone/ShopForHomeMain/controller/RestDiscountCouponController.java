package com.gl.capstone.ShopForHomeMain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl.capstone.ShopForHomeMain.model.DiscountCoupon;
import com.gl.capstone.ShopForHomeMain.model.UserDiscountCoupons;
import com.gl.capstone.ShopForHomeMain.remoteservice.RemoteDiscountService;

@RestController
public class RestDiscountCouponController {

	@Autowired
	private RemoteDiscountService remoteDiscountService;

	@PostMapping("/admin/setCoupon/{couponId}/{userid}")
	public String setCoupon(@PathVariable("couponId") long couponId, @PathVariable("userid") long userid) {
		return remoteDiscountService.assignCoupon(couponId, userid);
	}

	@GetMapping("/getCoupon/{redeemCode}")
	public DiscountCoupon getCoupon(@PathVariable("redeemCode") long redeemCode) {
		return remoteDiscountService.getCoupon(redeemCode);
	}

	@DeleteMapping("/admin/removeCoupon/{redeemCode}")
	public void removeCoupon(@PathVariable("redeemCode") long redeemCode) {
		remoteDiscountService.deleteUserCoupon(redeemCode);
	}

	@DeleteMapping("/admin/removeUserCoupons/{userId}")
	public void removeCoupons(@PathVariable("userId") long userId) {
		remoteDiscountService.deleteUserCoupons(userId);
	}

	@GetMapping("/getAllCoupons/{userId}")
	public List<UserDiscountCoupons> getAllCoupons(@PathVariable("userId") long userId) {
		return remoteDiscountService.getUserCoupons(userId);
	}

	@PostMapping("/admin/addCoupon")
	public String addCoupon(@RequestBody DiscountCoupon coupon) {
		return remoteDiscountService.addNewCoupon(coupon);
	}

	@DeleteMapping("/admin/delete/{couponId}")
	public String deleteCoupon(@PathVariable("couponId") long couponId) {
		return remoteDiscountService.deleteCoupon(couponId);
	}

	@GetMapping("/admin/getAllCouponTypes")
	public List<DiscountCoupon> getAllCouponTypes() {
		return remoteDiscountService.getAllCouponTypes();
	}
}

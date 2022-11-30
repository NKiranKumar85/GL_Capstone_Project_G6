package com.gl.capstone.ShopForHomeMain.model;

public class UserDiscountCoupons {

	private long reedemCode;
	private DiscountCoupon discountCoupon;
	private User user;

	public UserDiscountCoupons() {
		// TODO Auto-generated constructor stub
	}

	public UserDiscountCoupons(long reedemCode, DiscountCoupon discountCoupon, User user) {
		super();
		this.reedemCode = reedemCode;
		this.discountCoupon = discountCoupon;
		this.user = user;
	}

	public long getReedemCode() {
		return reedemCode;
	}

	public void setReedemCode(long reedemCode) {
		this.reedemCode = reedemCode;
	}

	public DiscountCoupon getDiscountCoupon() {
		return discountCoupon;
	}

	public void setDiscountCoupon(DiscountCoupon discountCoupon) {
		this.discountCoupon = discountCoupon;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

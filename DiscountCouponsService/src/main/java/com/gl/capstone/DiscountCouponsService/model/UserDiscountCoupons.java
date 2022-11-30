package com.gl.capstone.DiscountCouponsService.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class UserDiscountCoupons {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UDCseq")
	@SequenceGenerator(name = "UDCseq", initialValue = 2022004, allocationSize = 1)
	private long reedemCode;
	@ManyToOne
	private DiscountCoupon discountCoupon;
	@ManyToOne
	private User user;
	
	public UserDiscountCoupons() {
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

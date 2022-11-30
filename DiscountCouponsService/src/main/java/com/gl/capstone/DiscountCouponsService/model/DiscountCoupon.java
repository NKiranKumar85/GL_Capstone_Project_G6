package com.gl.capstone.DiscountCouponsService.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class DiscountCoupon {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DCseq")
	@SequenceGenerator(name = "DCseq", initialValue = 401, allocationSize = 1)
	private long couponId;
	private int percentage;
	private double minimumPurchase;
	private String description;

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public double getMinimumPurchase() {
		return minimumPurchase;
	}

	public void setMinimumPurchase(double minimumPurchase) {
		this.minimumPurchase = minimumPurchase;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}

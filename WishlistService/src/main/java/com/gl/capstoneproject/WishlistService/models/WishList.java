package com.gl.capstoneproject.WishlistService.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "WishListItems")
public class WishList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long wishListId;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;
	
	public WishList() {
		// TODO Auto-generated constructor stub
	}
	
	public WishList(long wishListId, User user, Product product) {
		super();
		this.wishListId = wishListId;
		this.user = user;
		this.product = product;
	}
	public long getWishListId() {
		return wishListId;
	}
	public void setWishListId(long wishListId) {
		this.wishListId = wishListId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

}

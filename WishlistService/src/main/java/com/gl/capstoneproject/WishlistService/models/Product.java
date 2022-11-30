package com.gl.capstoneproject.WishlistService.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productid;
	private String productName;
	private String Description;
	private double price;
	private int stock;
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "catogeryId")
	private Catogery catogery;

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public Catogery getCatogery() {
		return catogery;
	}

	public void setCatogery(Catogery catogery) {
		this.catogery = catogery;
	}

	@Override
	public String toString() {
		return "Product [productid=" + productid + ", productName=" + productName + ", Description=" + Description
				+ ", price=" + price + ", stock=" + stock + "]";
	}

}
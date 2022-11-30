package com.gl.capstone.ShopForHomeMain.model;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class UserCart {

	private Set<Product> products;
	private long coupon;
	private double totalAmount;
	
	public UserCart() {
		products = new LinkedHashSet<>();
		coupon = 0;
		totalAmount = 0;
	}

	public long getCoupon() {
		return coupon;
	}

	public void setCoupon(long coupon) {
		this.coupon = coupon;
	}
	
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void addProduct(Product product) {
		products.add(product);
		this.totalAmount = products.stream().collect(Collectors.summingDouble(product1 -> product1.getQuantity()*product1.getPrice()));
	}
	
	public void removeProduct(Product product) {
		products.remove(product);
	}
	
	public void updateQuantity(Product updatedproduct) {
		if(products.contains(updatedproduct)) {
			products.stream().filter( (product) -> 
			product.equals(updatedproduct)).forEach((p) -> p.setQuantity(updatedproduct.getQuantity()));
			this.totalAmount = products.stream().collect(Collectors.summingDouble(product1 -> product1.getQuantity()*product1.getPrice()));
		}else {
			addProduct(updatedproduct);
		}
	}
	
	public Product getProduct(long productId) {
		for(Product product : products) {
			if(product.getProductid() == productId)
				return product;
		}
		return null;
	}
	
	public void clearCart() {
		products.clear();
	}
	
	public Set<Product> getCartProducts(){
		return products;
	}
	
	public double getTotalAmount() {			
		return this.totalAmount;
	}
	
	public void applyDiscount(int percentage) {
		this.totalAmount = products.stream().collect(Collectors.summingDouble(product -> product.getQuantity()*product.getPrice()));
		totalAmount = totalAmount * (100-percentage)/100;
	}
}

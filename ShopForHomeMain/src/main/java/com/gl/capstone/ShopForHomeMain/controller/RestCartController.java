package com.gl.capstone.ShopForHomeMain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.capstone.ShopForHomeMain.model.RequestToken;
import com.gl.capstone.ShopForHomeMain.model.UserCart;
import com.gl.capstone.ShopForHomeMain.security.JwtTokenUtil;
import com.gl.capstone.ShopForHomeMain.service.UserCartService;

@RestController
@RequestMapping("/user/cart")
public class RestCartController {
	
	@Autowired
	private UserCartService cartService;
	
	@Autowired
	private UserCart cart;
	
	@PostMapping("/add/{productId}")
	public void addToCart(@PathVariable("productId") long productId) {
		cartService.addProductToCart(productId);
	}
	
	@PostMapping("/remove/{productId}")
	public void removeFromCart(@PathVariable("productId") long productId) {
		cartService.removeItemFromCart(productId);
	}
	
	@GetMapping("/products")
	public UserCart getCartProducts(){
		return cart;
	}
	
	@PutMapping("/checkOut")
	public void checkOut() {
		cartService.closeCart();
	}
	
	@PutMapping("/updateQuantity")
	public void updateQuantity(@RequestParam("productId") long productId, @RequestParam("quantity")int quantity) {
		cartService.updateQuantity(productId, quantity);
	}
	
	@PostMapping("/redeem/{redeemCode}")
	public String applyCoupon(@PathVariable("redeemCode")long redeemCode) {
		return cartService.applyCoupon(redeemCode);
	}
}

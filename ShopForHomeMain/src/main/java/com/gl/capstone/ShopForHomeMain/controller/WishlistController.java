package com.gl.capstone.ShopForHomeMain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl.capstone.ShopForHomeMain.model.Product;
import com.gl.capstone.ShopForHomeMain.remoteservice.RemoteWishlist;

@RestController
public class WishlistController {

	@Autowired
	private RemoteWishlist remoteWishlist;
	
	@PostMapping("/user/addToWishList")
	public void addToWishList(@RequestBody Product product) {
		remoteWishlist.addToWishList(product);
	}
	
	@GetMapping("/user/getWishlistProducts/{userId}")
	public List<Product> getWishlistProducts(@PathVariable("userId")long userId){
		return remoteWishlist.getAllWishlistProducts(userId);
	}
	
	@DeleteMapping("/user/removeWishlistItem/{productId}")
	public void removeWishlistItem(@PathVariable("productId")long productId) {
		remoteWishlist.removeWishlistProduct(productId);
	}
	
	@DeleteMapping("/user/removeWishlistItems/{userId}")
	public void removeWishlistItems(@PathVariable("userId")long userId) {
		remoteWishlist.deleteUserWishlist(userId);
	}
}

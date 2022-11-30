package com.gl.capstoneproject.WishlistService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl.capstoneproject.WishlistService.models.AuthenticatedUser;
import com.gl.capstoneproject.WishlistService.models.Product;
import com.gl.capstoneproject.WishlistService.services.WishListService;

@RestController
public class WIshListRestController {

	@Autowired
	private WishListService wishListService;
	
	@Autowired
	private AuthenticatedUser authenticatedUser;
	
	@PostMapping("/addToWishList")
	public void addToWishList(@RequestBody Product product) {
		wishListService.addProductToWishList(authenticatedUser.getUser(), product);
	}
	
	@GetMapping("/getAllWishListProducts/{userId}")
	public List<Product> getAllWishListProducts(@PathVariable("userId") long userId){
		return wishListService.getAllWishListProducts(userId);
	}
	
	@DeleteMapping("/removeWishListItem/{productId}")
	public void removeWishListItem(@PathVariable("productId")long productId) {
		wishListService.removeWishListItem(authenticatedUser.getUser().getUserid(), productId);
	}
	
	@DeleteMapping("/removeWishListItems/{userId}")
	public void removeWishListItems(@PathVariable("userId")long userId) {
		wishListService.deleteUserWishlist(userId);
	}
	
	@DeleteMapping("/deleteProduct/{productid}")
	public void deleteProduct(@PathVariable("productid")long productid) {
		wishListService.deleteWishListProduct(productid);
	}
	
}

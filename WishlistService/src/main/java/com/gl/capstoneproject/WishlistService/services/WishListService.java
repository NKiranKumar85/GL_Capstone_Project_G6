package com.gl.capstoneproject.WishlistService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.capstoneproject.WishlistService.models.Product;
import com.gl.capstoneproject.WishlistService.models.User;
import com.gl.capstoneproject.WishlistService.models.WishList;
import com.gl.capstoneproject.WishlistService.repositories.WishListRepository;

@Service
public class WishListService {

	@Autowired
	private WishListRepository wishListRepository;

	public void addProductToWishList(User user, Product product) {
		if (wishListRepository.getWishListProduct(user, product).isEmpty()) {
			WishList wishListItem = new WishList(0, user, product);
			wishListRepository.save(wishListItem);
		}
	}

	public List<Product> getAllWishListProducts(long userId) {
		User user = new User();
		user.setUserid(userId);
		return wishListRepository.getWishListProducts(user);
	}

	public void removeWishListItem(long userid, long productid) {
		wishListRepository.deleteWishListProducts(userid, productid);
	}
	
	public void deleteUserWishlist(long userId) {
		wishListRepository.deleteWishListProducts(userId);
	}
	
	public void deleteWishListProduct(long productid) {
		wishListRepository.deleteProduct(productid);
	}
	
	
}

package com.gl.capstoneproject.WishlistService.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.capstoneproject.WishlistService.models.Product;
import com.gl.capstoneproject.WishlistService.models.User;
import com.gl.capstoneproject.WishlistService.models.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long>{
	
	@Modifying
	@Transactional
	@Query("delete from WishList wl where wl.user.userid = ?1 and wl.product.productid =?2")
	public void deleteWishListProducts(long userid, long productid);
	
	@Query("select wl.product from WishList wl where wl.user = ?1")
	public List<Product> getWishListProducts(User user);
	
	@Query("select wl.product from WishList wl where wl.user = ?1 and wl.product = ?2")
	public Optional<WishList> getWishListProduct(User user, Product product);
	
	@Modifying
	@Transactional
	@Query("delete from WishList wl where wl.user.userid = ?1")
	public void deleteWishListProducts(long userId);
	
	@Modifying
	@Transactional
	@Query("delete from WishList wl where wl.product.productid =?1")
	public void deleteProduct(long productid);
}

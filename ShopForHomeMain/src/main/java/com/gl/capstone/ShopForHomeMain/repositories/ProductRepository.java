package com.gl.capstone.ShopForHomeMain.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.capstone.ShopForHomeMain.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Modifying
	@Transactional
	@Query("update Product p set p.stock=p.stock-?1 where p.productid = ?2")
	public void updateStocks(int quantity, long productid);
	
	public List<Product> findByOrderByPrice();
	
	public List<Product> findByOrderByPriceDesc();
	
	@Query("select p from Product p where p.catogery.catogeryId=?1")
	public List<Product> getAllCatogeryProducts(long catogeryId);
}

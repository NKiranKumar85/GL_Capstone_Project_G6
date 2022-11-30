package com.gl.capstone.ShopForHomeMain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.capstone.ShopForHomeMain.model.Catogery;
import com.gl.capstone.ShopForHomeMain.model.Product;
import com.gl.capstone.ShopForHomeMain.remoteservice.RemoteWishlist;
import com.gl.capstone.ShopForHomeMain.repositories.CatogeryRepositroy;
import com.gl.capstone.ShopForHomeMain.repositories.OrderItemRepository;
import com.gl.capstone.ShopForHomeMain.repositories.ProductRepository;


@Component
public class ProductServiceImpl {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private CatogeryRepositroy catogeryRepositroy;
	
	@Autowired
	private RemoteWishlist remoteWishlist;
	
	public Product getUserService(long productId) {
		Optional<Product> product = productRepository.findById(productId);
		return product.isPresent() ? product.get() : null;
	}
	
	public Product saveProductService(Product product) {
		Optional<Catogery> catogery = catogeryRepositroy.findById(product.getCatogery().getCatogeryId());
		if(catogery.isPresent()) {
			product.setCatogery(catogery.get());
		}
		return productRepository.save(product);
	}
	
	public void deleteByIdService(long productId) {
		System.out.println("Coming");
		remoteWishlist.deleteProduct(productId);
		System.out.println("Coming");
		orderItemRepository.deleteProductOrder(productId);
		System.out.println("Coming");
		productRepository.deleteById(productId);
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public List<Product> sortProducts(){
		return productRepository.findByOrderByPrice();
	}
	
	public List<Product> sortProductDesc(){
		return productRepository.findByOrderByPriceDesc();
	}
	
	public List<Catogery> getAllCatogeries(){
		return catogeryRepositroy.findAll();
	}
	
	public List<Product> getAllCatogeryProducts(long catogeryId){
		return productRepository.getAllCatogeryProducts(catogeryId);
	}
	
	public Catogery addCatogery(Catogery catogery) {
		if(catogeryRepositroy.findByCatogeryname(catogery.getCatogeryName()).isEmpty()) {			
			return catogeryRepositroy.save(catogery);
		}
		return null;
	}
}

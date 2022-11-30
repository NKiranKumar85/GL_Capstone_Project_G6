package com.gl.capstone.ShopForHomeMain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.capstone.ShopForHomeMain.model.Catogery;
import com.gl.capstone.ShopForHomeMain.model.Product;
import com.gl.capstone.ShopForHomeMain.service.ProductServiceImpl;

@RestController
public class ProductController {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@PostMapping("/admin/product/save")
	public Product saveProduct(@RequestBody Product product) {
		return productServiceImpl.saveProductService(product);
	}

	@GetMapping("/getProduct")
	public Product getProduct(@RequestParam("productId")long productId) {
		return productServiceImpl.getUserService(productId);
	}

	@GetMapping("/allProducts")
	public List<Product> getAllProduct() {
		return productServiceImpl.getAllProducts();
	}
	
	@DeleteMapping("/admin/product/delete")
	public void deleteProduct(@RequestParam("productId")long productId) {
		productServiceImpl.deleteByIdService(productId);
	}
	
	@PutMapping("/admin/product/update")
	public Product updateProduct(@RequestBody Product product) {
		return productServiceImpl.saveProductService(product);
	}
	
	@GetMapping("/sortLowToHigh")
	public List<Product> sortLowToHigh(){
		return productServiceImpl.sortProducts();
	}
	
	@GetMapping("/sortHighToLow")
	public List<Product> sortHighToLow(){
		return productServiceImpl.sortProductDesc();
	}
	
	@GetMapping("/getAllCatogeries")
	public List<Catogery> getAllCatogeries(){
		return productServiceImpl.getAllCatogeries();
	}
	
	@GetMapping("/getAllCatogeryProducts/{catogeryId}")
	public List<Product> getAllCatogeryProducts(@PathVariable("catogeryId")long catogeryId){
		return productServiceImpl.getAllCatogeryProducts(catogeryId);
	}
	
	@PostMapping("/addCaotgery")
	public Catogery addCatogery(@RequestBody Catogery catogery) {
		return productServiceImpl.addCatogery(catogery);
	}
}

package com.gl.capstone.ShopForHomeMain.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.capstone.ShopForHomeMain.model.DiscountCoupon;
import com.gl.capstone.ShopForHomeMain.model.Order;
import com.gl.capstone.ShopForHomeMain.model.OrderItem;
import com.gl.capstone.ShopForHomeMain.model.Product;
import com.gl.capstone.ShopForHomeMain.model.RequestToken;
import com.gl.capstone.ShopForHomeMain.model.User;
import com.gl.capstone.ShopForHomeMain.model.UserCart;
import com.gl.capstone.ShopForHomeMain.remoteservice.RemoteDiscountService;
import com.gl.capstone.ShopForHomeMain.repositories.OrderItemRepository;
import com.gl.capstone.ShopForHomeMain.repositories.OrderRepository;
import com.gl.capstone.ShopForHomeMain.repositories.ProductRepository;
import com.gl.capstone.ShopForHomeMain.repositories.UserRepository;
import com.gl.capstone.ShopForHomeMain.security.JwtTokenUtil;

@Component
public class UserCartService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RemoteDiscountService discountService;
	
	@Autowired
	private UserCart cart;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private RequestToken requestToken;
	
	public void addProductToCart(long productId) {
		Optional<Product> product = productRepository.findById(productId);
		
		if(product.isPresent() && cart.getCoupon()==0) {
			Product updatedProduct = product.get();
			updatedProduct.setQuantity(1);
			cart.addProduct(updatedProduct);
		}
		
		if(product.isPresent() && cart.getCoupon()!=0) {
			Product updatedProduct = product.get();
			updatedProduct.setQuantity(1);
			cart.addProduct(updatedProduct);
			applyCoupon(cart.getCoupon());
		}
	}
	
	public void removeItemFromCart(long productId) {
		Optional<Product> product = productRepository.findById(productId);
		if(product.isPresent()) {
			cart.removeProduct(product.get());
		}
	}
	
	public void updateQuantity(long productId, int quantity) {
		Product product = cart.getProduct(productId);
		if(product != null && quantity > 0) {
			product.setQuantity(quantity);
			cart.updateQuantity(product);
			if(cart.getCoupon()!=0) {
				applyCoupon(cart.getCoupon());
			}
		}
	}
	
	public Set<Product> getCartProducts(){
		return cart.getCartProducts();
	}
	
	public void closeCart() {
		String authenticatedUsername = jwtTokenUtil.getUsernameFromToken(requestToken.getToken());
		User user = userRepository.findByUsername(authenticatedUsername);
		if(user != null){			
			Order order = new Order();
			order.setOrderDate(Date.valueOf(LocalDate.now()));
			order.setTotalAmount(cart.getTotalAmount());
			order.setUser(user);
			Order savedOrder =  orderRepository.save(order);
			saveHistory(cart, savedOrder);
		}
		if(cart.getCoupon()!=0) {
			discountService.deleteUserCoupon(cart.getCoupon());
		}
	}
	
	public void saveHistory(UserCart cart, Order order) {
		for(Product product : cart.getCartProducts()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setDate(order.getOrderDate());
			orderItem.setPrice(product.getPrice());
			orderItem.setProduct(product);
			orderItem.setQuantity(product.getQuantity());
			orderItem.setSubTotal(product.getPrice()*product.getQuantity());
			orderItemRepository.save(orderItem);
			productRepository.updateStocks(product.getQuantity(), product.getProductid());
		}
	}
	
	public String applyCoupon(long redeemCode) {
		DiscountCoupon discountCoupon = discountService.getCoupon(redeemCode);
		if(cart.getCartProducts().size()>0 && cart.getTotalAmount()>discountCoupon.getMinimumPurchase()) {
			cart.setCoupon(redeemCode);
			cart.applyDiscount(discountCoupon.getPercentage());
			return "coupon applied";
		}else {
			return "Please make minimum purchase";
		}
	}
	
}
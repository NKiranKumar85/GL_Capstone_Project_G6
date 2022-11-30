package com.gl.capstone.ShopForHomeMain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.capstone.ShopForHomeMain.model.User;
import com.gl.capstone.ShopForHomeMain.remoteservice.RemoteDiscountService;
import com.gl.capstone.ShopForHomeMain.remoteservice.RemoteWishlist;
import com.gl.capstone.ShopForHomeMain.repositories.OrderItemRepository;
import com.gl.capstone.ShopForHomeMain.repositories.OrderRepository;
import com.gl.capstone.ShopForHomeMain.repositories.UserRepository;

@Component
public class UserServiceImpl {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private RemoteDiscountService discountService;
	
	@Autowired
	private RemoteWishlist remoteWishlist;
	
	public User getUserService(long userid) {
		Optional<User> user = userRepository.findById(userid);
		return user.isPresent() ? user.get() : null;
	}
	
	public User saveUserService(User user) {
		return userRepository.save(user);
	}
	
	public void deleteUserService(long userid) {
		List<Long> orderId = orderRepository.getOrderId(userid);
		if(orderId != null) {		
			System.out.println("Com");
			orderId.forEach( id -> orderItemRepository.deleteById(id));
		}
		System.out.println("Com");
		remoteWishlist.deleteUserWishlist(userid);
		discountService.deleteUserCoupons(userid);
		orderRepository.deleteUserOrders(userid);
		userRepository.delete(userid);
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
}

package com.gl.capstone.ShopForHomeMain.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.capstone.ShopForHomeMain.model.Order;
import com.gl.capstone.ShopForHomeMain.model.OrderItem;
import com.gl.capstone.ShopForHomeMain.repositories.OrderItemRepository;
import com.gl.capstone.ShopForHomeMain.repositories.OrderRepository;

@Service
public class OrdersService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public List<Order> getOrders(Date start, Date end){
		return orderRepository.getOrdersInRange(start, end);
	}
	
	public List<OrderItem> getOrderDetails(long orderID){
		return orderItemRepository.getOrderDate(orderID);
	}
}

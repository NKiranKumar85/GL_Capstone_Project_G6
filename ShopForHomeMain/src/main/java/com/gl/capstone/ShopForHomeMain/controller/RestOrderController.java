package com.gl.capstone.ShopForHomeMain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.capstone.ShopForHomeMain.model.DateModel;
import com.gl.capstone.ShopForHomeMain.model.Order;
import com.gl.capstone.ShopForHomeMain.model.OrderItem;
import com.gl.capstone.ShopForHomeMain.service.OrdersService;

@RestController
public class RestOrderController {

	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping("/admin/orders")
	public List<Order> getOrders(@RequestBody DateModel dateModel){
		return ordersService.getOrders(dateModel.getStartDate(), dateModel.getEndDate());
	}
	
	@GetMapping("/admin/orderDescription/{orderId}")
	public List<OrderItem> getOrderDescription(@PathVariable("orderId")long orderId){
		return ordersService.getOrderDetails(orderId);
	}
	
}

package com.gl.capstone.ShopForHomeMain.repositories;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.capstone.ShopForHomeMain.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	@Modifying
	@Transactional
	@Query("delete from Order o where o.user.userid = ?1")
	public void deleteUserOrders(long userid);
	
	@Query("select o.orderId from Order o where o.user.userid = ?1")
	public List<Long> getOrderId(long userId);
	
	@Query("select o from Order o where o.orderDate between ?1 and ?2")
	public List<Order> getOrdersInRange(Date start, Date end);
}

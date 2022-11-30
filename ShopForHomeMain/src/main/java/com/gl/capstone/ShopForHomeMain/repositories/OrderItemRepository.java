package com.gl.capstone.ShopForHomeMain.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.capstone.ShopForHomeMain.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

	@Modifying
	@Transactional
	@Query("delete from OrderItem o where o.order.orderId = ?1")
	public void deleteOrderItems(long orderId);
	
	@Modifying
	@Transactional
	@Query("delete from OrderItem o where o.product.productid = ?1")
	public void deleteProductOrder(long productId);
	
	@Query("select o from OrderItem o where o.order.orderId=?1")
	public List<OrderItem> getOrderDate(long orderid);
}

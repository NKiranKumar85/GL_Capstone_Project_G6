package com.gl.capstone.ShopForHomeMain.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.capstone.ShopForHomeMain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	@Modifying
	@Transactional
	@Query("delete from User u where u.userid = ?1")
	public void delete(long id);
	
	public User findByUsername(String username);
}

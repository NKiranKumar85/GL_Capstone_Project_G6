package com.gl.capstoneproject.WishlistService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.capstoneproject.WishlistService.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);
}

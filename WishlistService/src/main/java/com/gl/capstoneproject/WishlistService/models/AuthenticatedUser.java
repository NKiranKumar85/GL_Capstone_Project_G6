package com.gl.capstoneproject.WishlistService.models;

import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUser {

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}

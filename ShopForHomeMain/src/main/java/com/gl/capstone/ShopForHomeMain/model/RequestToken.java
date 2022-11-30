package com.gl.capstone.ShopForHomeMain.model;

import org.springframework.stereotype.Component;

@Component
public class RequestToken {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}

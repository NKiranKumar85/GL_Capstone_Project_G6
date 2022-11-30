package com.gl.capstone.ShopForHomeMain.remoteservice;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.gl.capstone.ShopForHomeMain.model.Product;
import com.gl.capstone.ShopForHomeMain.model.RequestToken;

public class RemoteWishlist {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private RequestToken requestToken;
	private String serviceURL;

	public RemoteWishlist(String serviceURL) {
		this.serviceURL = serviceURL.startsWith("http") ? serviceURL : "http://" + serviceURL;
	}

	public void addToWishList(Product product) {
		restTemplate.exchange(serviceURL + "/addToWishList", HttpMethod.POST,
				new HttpEntity<Product>(product, setToken()), Object.class);
	}

	public void removeWishlistProduct(long productId) {
		restTemplate.exchange(serviceURL + "/removeWishListItem/" + productId, HttpMethod.DELETE,
				new HttpEntity<>(setToken()), Object.class);
	}

	public List<Product> getAllWishlistProducts(long userId) {
		Product[] products = restTemplate.exchange(serviceURL + "/getAllWishListProducts/" + userId, HttpMethod.GET,
				new HttpEntity<>(setToken()), Product[].class).getBody();
		return Arrays.asList(products);
	}

	public void deleteUserWishlist(long userId) {
		restTemplate.exchange(serviceURL + "/removeWishListItems/" + userId, HttpMethod.DELETE,
				new HttpEntity<>(setToken()), Object.class);
	}

	public void deleteProduct(long productid) {
		restTemplate.exchange(serviceURL + "/deleteProduct/" + productid, HttpMethod.DELETE,
				new HttpEntity<>(setToken()), Object.class);
	}

	public HttpHeaders setToken() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + requestToken.getToken());
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}
}

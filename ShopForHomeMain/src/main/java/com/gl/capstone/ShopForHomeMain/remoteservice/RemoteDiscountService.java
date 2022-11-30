package com.gl.capstone.ShopForHomeMain.remoteservice;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.gl.capstone.ShopForHomeMain.model.DiscountCoupon;
import com.gl.capstone.ShopForHomeMain.model.RequestToken;
import com.gl.capstone.ShopForHomeMain.model.UserDiscountCoupons;

public class RemoteDiscountService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private RequestToken requestToken;
	private String serviceURL;
	
	public RemoteDiscountService() {
		// TODO Auto-generated constructor stub
	}

	public RemoteDiscountService(String serviceURL) {
		this.serviceURL = serviceURL.startsWith("http") ? serviceURL
				: "http://" + serviceURL;
	}
	
	public String assignCoupon(long couponId, long userid) {
		ResponseEntity<String> re = restTemplate.exchange(serviceURL+"/admin/setUserCoupons/"+couponId+"/"+userid,HttpMethod.POST , new HttpEntity<>(setToken()), String.class);
		return re.getBody();
	}
	
	public void deleteUserCoupon(long couponId) {
		restTemplate.exchange(serviceURL+"/removeUserCoupon/"+couponId,HttpMethod.DELETE, new HttpEntity<>(setToken()), Object.class);
	}
	
	public void deleteUserCoupons(long userId) {
		restTemplate.exchange(serviceURL+"/removeAllUserCoupon/"+userId,HttpMethod.DELETE, new HttpEntity<>(setToken()), Object.class);
	}
	
	public List<UserDiscountCoupons> getUserCoupons(long userId){
		UserDiscountCoupons[] coupons = restTemplate.exchange(serviceURL+"/getUserCoupons/"+userId,HttpMethod.GET, new HttpEntity<>(setToken()), UserDiscountCoupons[].class).getBody();
		return Arrays.asList(coupons);
	}
	
	public DiscountCoupon getCoupon(long redeemCode) {
		return restTemplate.exchange(serviceURL+"/getUserCoupon/"+redeemCode,HttpMethod.GET, new HttpEntity<>(setToken()), DiscountCoupon.class).getBody();
	}
	
	public String addNewCoupon(DiscountCoupon discountCoupon) {
		return restTemplate.exchange(serviceURL+"/admin/addNewCoupon", HttpMethod.POST, new HttpEntity<Object>(discountCoupon, setToken()), String.class).getBody();
	}
	
	public String deleteCoupon(long couponId) {
		return restTemplate.exchange(serviceURL+"/admin/deleteCoupon/"+couponId, HttpMethod.DELETE, new HttpEntity<String>(setToken()), String.class).getBody();
	}
	
	public List<DiscountCoupon> getAllCouponTypes(){
		DiscountCoupon[] couponsTypes = restTemplate.exchange(serviceURL+"/admin/getAllCouponTypes", HttpMethod.GET, new HttpEntity<String>(setToken()), DiscountCoupon[].class).getBody();
		return Arrays.asList(couponsTypes);
	}
	
	public HttpHeaders setToken() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(requestToken.getToken());
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}
}

 
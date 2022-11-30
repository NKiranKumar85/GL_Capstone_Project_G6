package com.gl.capstone.ShopForHomeMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.gl.capstone.ShopForHomeMain.remoteservice.RemoteDiscountService;
import com.gl.capstone.ShopForHomeMain.remoteservice.RemoteWishlist;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class ShopForHomeMainApplication {

	public static final String
	WISHLIST_SERVICE_URL = "http://WISHLIST-MICROSERVICES-PRODUCER";
	
	public static final String
	DISCOUNTCOUPON_SERVICE_URL = "http://DISCOUNTCOUPON-MICROSERVICES-PRODUCER";
	
	public static void main(String[] args) {
		SpringApplication.run(ShopForHomeMainApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public RemoteWishlist getRemoteWishlist() {
		return new RemoteWishlist(WISHLIST_SERVICE_URL);
	}
	
	@Bean
	public RemoteDiscountService getDiscountService() {
		return new RemoteDiscountService(DISCOUNTCOUPON_SERVICE_URL);
	}
	

	@Bean
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	      .apiInfo(apiInfo())
	      .securityContexts(Arrays.asList(securityContext()))
	      .securitySchemes(Arrays.asList(apiKey()))
	      .select()
	      .apis(RequestHandlerSelectors.any())
	      .paths(PathSelectors.any())
	      .build();
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	    	      "Api Documentation",
	    	      "Api Documentation",
	    	      "1.0",
	    	      "urn:tos",
	    	      new springfox.documentation.service.Contact("Kirankumar", "localhost.com", "kiran@mail.com"),
	    	      "Apache 2.0",
	    	      "http://www.apache.org/licenses/LICENSE-2.0",
	    	      new ArrayList<>());
	}
	private ApiKey apiKey() { 
	    return new ApiKey("JWT", "Authorization", "Header"); 
	}
	
	private SecurityContext securityContext() { 
	    return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
	} 

	private List<SecurityReference> defaultAuth() { 
	    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
	    authorizationScopes[0] = authorizationScope; 
	    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
	}
}

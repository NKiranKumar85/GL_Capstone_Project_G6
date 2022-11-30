package com.gl.capstone.ShopForHomeMain.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.capstone.ShopForHomeMain.model.LoginCredentials;
import com.gl.capstone.ShopForHomeMain.model.RequestToken;
import com.gl.capstone.ShopForHomeMain.model.Role;
import com.gl.capstone.ShopForHomeMain.model.User;
import com.gl.capstone.ShopForHomeMain.security.JwtTokenUtil;
import com.gl.capstone.ShopForHomeMain.service.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private Role role;
	
	@Autowired
	private RequestToken requestToken;
	
	@PostMapping("/admin/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginCredentials authenticationRequest, HttpSession session) throws Exception {
		role.setRole("ROLE_USER");
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(token);
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<String> loginAdmin(@RequestBody LoginCredentials authenticationRequest, HttpSession session) throws Exception {
		role.setRole("ROLE_ADMIN");
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(token);
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	@PostMapping("/admin/save")
	public User registerUser(@RequestBody User userObject) {
		userObject.setRole("ROLE_ADMIN");
		userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
		return userServiceImpl.saveUserService(userObject);
	}
	
	@PostMapping("/user/save")
	public User saveUser(@RequestBody User userObject) {
		userObject.setRole("ROLE_USER");
		userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
		return userServiceImpl.saveUserService(userObject);
	}
	
	@GetMapping("/admin/getUser")
	public User getUser(@RequestParam("userId") long userId) {
		return userServiceImpl.getUserService(userId);
	}
	
	@GetMapping("/admin/getAllUser")
	public List<User> getAllUser() {
		return userServiceImpl.getAllUsers();
	}
	
	@PutMapping("/updateUser")
	public User updateUser(@RequestBody User userObject) {
		return userServiceImpl.saveUserService(userObject);
	}
	
	@DeleteMapping("/admin/deleteUser")
	public void deleteUser(@RequestParam("userId")long userId) {
		userServiceImpl.deleteUserService(userId);
	}
	
	@GetMapping("/logoutUser")
	public String logoutUser(HttpServletRequest request, HttpServletResponse response) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    if (auth != null){    
		        new SecurityContextLogoutHandler().logout(request, response, auth);
		        requestToken.setToken(null);
		        role.setRole(null);
		        return "user logged out";
		    }else {
		    	return "no user logged in";
		    }
	}
}

package com.gl.capstone.ShopForHomeMain.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gl.capstone.ShopForHomeMain.model.Role;
import com.gl.capstone.ShopForHomeMain.model.User;
import com.gl.capstone.ShopForHomeMain.repositories.UserRepository;

@Component
public class JwtUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Role role;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null || user.getRole().equals(role.getRole())) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		return new org.springframework.security.core.userdetails.User
				(user.getUsername(), user.getPassword(), authorities);
	}

}

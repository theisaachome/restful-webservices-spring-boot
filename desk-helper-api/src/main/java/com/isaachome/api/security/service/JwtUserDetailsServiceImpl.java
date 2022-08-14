package com.isaachome.api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.isaachome.api.entity.User;
import com.isaachome.api.security.jwt.JwtUserFactory;
import com.isaachome.api.service.UserService;

@Component
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findbyEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username %", username));
		} else {
			return JwtUserFactory.create(user);
		}
	}

}

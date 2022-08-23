package com.isaachome.api.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isaachome.api.entity.User;
import com.isaachome.api.security.jwt.JwtAuthenticationRequest;
import com.isaachome.api.security.jwt.JwtTokenUtil;
import com.isaachome.api.security.model.CurrentUser;
import com.isaachome.api.service.UserService;

@RestController
@CrossOrigin(origins="*")
public class AuthenticationRestController {
	

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	

	@Autowired
	private UserService userService;
	
	

	@PostMapping(value="/api/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authRequest) {
		
		var authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authRequest.email(), authRequest.password()
						));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenUtil.generateToken(userDetailsService.loadUserByUsername(authRequest.email()));
		User user = userService.findbyEmail(authRequest.email());
		user.setPassword(null);
		return ResponseEntity.ok(new CurrentUser(token,user));
	}

	@PostMapping(value="/api/refresh")
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request){
		String token = request.getHeader("Authorization");
		String username = jwtTokenUtil.getUsernameFromToken(token);
		final User user = userService.findbyEmail(username);
		
		if(jwtTokenUtil.canTokenBeRefreshed(token)) {
			String refreshedToken = jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new CurrentUser(refreshedToken,user));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	

}





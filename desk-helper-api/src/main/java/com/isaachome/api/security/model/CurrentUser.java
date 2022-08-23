package com.isaachome.api.security.model;

import com.isaachome.api.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrentUser {


	private String token;
	private User user;
	
}

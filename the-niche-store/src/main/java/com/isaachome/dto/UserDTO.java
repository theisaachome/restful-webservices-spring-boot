package com.isaachome.dto;

import java.time.LocalDateTime;

import com.isaachome.entity.User;

public record UserDTO(String email,String country,String username,AddressDTO address) {

	public User convertToUser() {
		return User.builder()
				.email(email)
				.country(country)
				.fullname(username)
				.createdTime(LocalDateTime.now())
				.address(address ==null?null:address.convertToAddress())
				.build();
	}
}

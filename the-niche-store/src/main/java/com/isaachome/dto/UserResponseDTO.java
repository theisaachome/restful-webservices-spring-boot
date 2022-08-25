package com.isaachome.dto;

import com.isaachome.entity.User;

public record UserResponseDTO(
		String id,
		String email,
	     String country,
	     String username
		) {

	public UserResponseDTO convertToResponse(User user) {
		return new UserResponseDTO(user.getId(), user.getEmail(), user.getCountry(), user.getFullname());
	}
}

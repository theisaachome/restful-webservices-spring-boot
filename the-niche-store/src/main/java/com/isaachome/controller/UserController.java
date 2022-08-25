package com.isaachome.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isaachome.dto.UserDTO;
import com.isaachome.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) {
		userService.saveUser(user.convertToUser());
		return ResponseEntity.ok(true);
	}

	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(userService.getAll());
	}

	@GetMapping("/{country}")
	public ResponseEntity<?> findByCountry(@PathVariable String country) {
		return ResponseEntity.ok(userService.findUsersByCountry(country));
	}

	@GetMapping("/count-by-country")
	public ResponseEntity<?> countByCountry() {
		return ResponseEntity.ok(userService.countByCountry());
	}

	@GetMapping("group-by-country")
	public ResponseEntity<?> groupByCountry() {
		return ResponseEntity.ok(userService.groupByCountry());
	}
}

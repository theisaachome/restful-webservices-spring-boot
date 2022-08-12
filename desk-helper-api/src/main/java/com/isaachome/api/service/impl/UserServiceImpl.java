package com.isaachome.api.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.isaachome.api.entity.User;
import com.isaachome.api.repos.UserRepo;
import com.isaachome.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public User findbyEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public User createOrUpdate(User user) {
		return userRepo.save(user);
	}

	@Override
	public User findById(String id) {
		return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("No User Found with id " + id));
	}

	@Override
	public void delete(String id) {
		this.userRepo.deleteById(id);
	}

	@Override
	public Page<User> findAll(int page, int count) {
		Pageable pages = PageRequest.of(page, count);
		return userRepo.findAll(pages);
	}
}

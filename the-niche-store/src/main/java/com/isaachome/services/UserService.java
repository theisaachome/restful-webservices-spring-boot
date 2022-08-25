package com.isaachome.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaachome.dto.UserResponseDTO;
import com.isaachome.entity.User;
import com.isaachome.projection.CountryAggregation;
import com.isaachome.projection.UserAggregation;
import com.isaachome.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	public void saveUser(User user) {
		userRepo.save(user);
	}
	
	public List<User>  getAll(){
		return userRepo.findAll();
	}
	
	public List<User> findUsersByCountry(String country){
		log.info(userRepo.findByCountryAsCustom(country).toString());
		return userRepo.findByCountryAsCustom(country);
	}
	
	public List<UserAggregation> groupByCountry(){
		log.info(userRepo.groupByCountry().toString());
		return userRepo.groupByCountry();
	}
	public List<CountryAggregation> countByCountry(){
		return userRepo.countByCountry();
	}
}

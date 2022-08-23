package com.isaachome.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaachome.entity.User;
import com.isaachome.projection.CountryAggregation;
import com.isaachome.projection.UserAggregation;
import com.isaachome.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	public void saveUser(User user) {
		userRepo.save(user);
	}
	
	public List<User> findUsersByCountry(String country){
		return userRepo.findByCountryAsCustom(country);
	}
	
	public List<UserAggregation> groupByCountry(){
		return userRepo.groupByCountry();
	}
	public List<CountryAggregation> countByCountry(){
		return userRepo.countByCountry();
	}
}

package com.isaachome.api.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.isaachome.api.entity.User;

public interface UserRepo extends MongoRepository<User, String>{

	User  findByEmail(String email);
}

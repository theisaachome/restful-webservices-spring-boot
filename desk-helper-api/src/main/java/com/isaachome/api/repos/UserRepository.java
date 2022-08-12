package com.isaachome.api.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.isaachome.api.entity.User;

public interface UserRepository extends MongoRepository<User, String>{

	User  findByEmail(String email);
}

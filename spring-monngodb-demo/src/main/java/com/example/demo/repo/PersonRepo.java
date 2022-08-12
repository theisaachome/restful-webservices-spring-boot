package com.example.demo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.collection.Person;

public interface PersonRepo extends MongoRepository<Person, String> {

	List<Person> findByFirstNameStartsWith(String name);
}

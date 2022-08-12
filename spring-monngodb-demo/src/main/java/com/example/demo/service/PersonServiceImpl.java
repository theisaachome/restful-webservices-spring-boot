package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import com.example.demo.collection.Person;
import com.example.demo.repo.PersonRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PersonServiceImpl  implements PersonService{

	private final PersonRepo personRepo;
	@Override
	public String save(Person person) {
		System.out.println(person);
		return personRepo.save(person).getPersonId();
	}

	@Override
	public List<Person> getPersonStartWith(String name) {
		return personRepo.findByFirstNameStartsWith(name);
	}

	@Override
	public void delete(String id) {
		personRepo.deleteById(id);
	}

	@Override
	public List<Person> getByPersonAge(Integer minAge, Integer maxAge) {
		return null;
	}

	@Override
	public Page<Person> search(String name, Integer minAge, Integer maxAge, String city, Pageable pageable) {
		return null;
	}

	@Override
	public List<Document> getOldestPersonByCity() {
		return null;
	}

	@Override
	public List<Document> getPopulationByCity() {
		return null;
	}

}

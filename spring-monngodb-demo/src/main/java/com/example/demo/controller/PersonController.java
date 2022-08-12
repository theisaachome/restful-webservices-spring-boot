package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.collection.Person;
import com.example.demo.service.PersonService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/persons")
@AllArgsConstructor
public class PersonController {

	
	private final PersonService personService;
	
	@PostMapping
	public String save(@RequestBody Person person) {
		System.out.println(person);
		return personService.save(person);
	}
	
	@GetMapping
	public List<Person> getPersonStartWith(@RequestParam("name")String name) {
		System.out.println(name);
		return personService.getPersonStartWith(name);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id")String id) {
		personService.delete(id);
	}
}

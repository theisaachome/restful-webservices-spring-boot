package com.isaachome.balance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.isaachome.balance.service.AbstractService;

public abstract class AbstractController<T,ID extends Number> {

	private AbstractService<T, ID> service;
	
	public AbstractController(AbstractService<T, ID> service) {
		this.service =service;
	}
	@PostMapping
	public T create(@RequestBody T data) {
		return service.save(data);
	}

	@PutMapping("{id}")
	public T update(@PathVariable ID id, @RequestBody T data) {
		return service.update(id, data);
	}
	
	@GetMapping("{id}")
	public T findById(@PathVariable ID id) {
		return service.findById(id);
	}
}

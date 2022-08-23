package com.isaachome.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.isaachome.entity.Order;

public interface OrderRepo extends MongoRepository<Order, String> {

	List<Order> findByUserId(String userId);
}

package com.isaachome.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaachome.entity.Order;
import com.isaachome.repo.OrderRepo;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderRepo;
	
	public void saveOrder(Order order) {
		orderRepo.save(order);
	}
	public List<Order> ordersOfUser(String userId){
		return orderRepo.findByUserId(userId);
	}
}

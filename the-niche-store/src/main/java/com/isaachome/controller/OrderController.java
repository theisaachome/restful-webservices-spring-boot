package com.isaachome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isaachome.dto.OrderDTO;
import com.isaachome.services.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<?> saveOrder(@RequestBody OrderDTO orderDTO) {
		orderService.saveOrder(orderDTO.convertToOrderEntity());
		return ResponseEntity.ok(true);
	}
	
	@GetMapping("{userId}")
	public ResponseEntity<?> getOrdersOfUser(@PathVariable("userId") String userId){
		return ResponseEntity.ok(orderService.ordersOfUser(userId));
	}
}

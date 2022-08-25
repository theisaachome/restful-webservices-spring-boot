package com.isaachome.dto;

import java.time.LocalDateTime;

import com.isaachome.entity.Order;

public record OrderDTO(String name, String userId) {

	public Order convertToOrderEntity() {
		return Order.builder()
				.orderName(name)
				.userId(userId)
				.createdTime(LocalDateTime.now())
				.build();
	}

}

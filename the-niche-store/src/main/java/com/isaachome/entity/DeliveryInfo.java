package com.isaachome.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DeliveryInfo {

	private LocalDate deliveryDate;
	private int deliveryFee;
	private boolean inStock;
	
}

package com.isaachome.dto;

import com.isaachome.entity.Address;

public record AddressDTO(String city,Integer postCode) {
	public Address convertToAddress() {
		return Address.builder().city(city).postCode(postCode).build();
	}
}

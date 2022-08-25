package com.isaachome.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isaachome.entity.LegoSet;
import com.isaachome.services.LegoSetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/legosets")
@RequiredArgsConstructor
public class LegoSetController {

	
	private final LegoSetService legoSetService;
	
	@PostMapping
	public LegoSet inser(@RequestBody LegoSet legoSet) {
		return legoSetService.create(legoSet);
	}
}

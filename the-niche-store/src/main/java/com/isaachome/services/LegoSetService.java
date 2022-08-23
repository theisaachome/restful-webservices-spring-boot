package com.isaachome.services;

import org.springframework.stereotype.Service;

import com.isaachome.entity.LegoSet;
import com.isaachome.repo.LegoSetRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LegoSetService {

	private final LegoSetRepo legoSetRepo;
	
	public LegoSet create(LegoSet legoSet) {
		return legoSetRepo.insert(legoSet);
	}
}

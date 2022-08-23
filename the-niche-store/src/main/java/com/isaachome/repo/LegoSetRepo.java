package com.isaachome.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.isaachome.entity.LegoSet;

public interface LegoSetRepo extends MongoRepository<LegoSet,String> {

}

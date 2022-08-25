package com.isaachome.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.isaachome.entity.User;
import com.isaachome.projection.CountryAggregation;
import com.isaachome.projection.UserAggregation;

public interface UserRepo extends MongoRepository<User, String> {

	// findBy + field
	Optional<User> findByFullname(String fullname);

	// value where condition
	// fields: select items: 1 => included, 0 => exclude
	// sort:1 => ASC , -1 => DESC
	@Query(value = "{country : ?0 }", fields = "{ email: 1,_id:0 }", sort = "{email:-1}")
	List<User> findByCountryAsCustom(String country);

	// select country, sum(1) =count(*) from users group by country
	@Aggregation("{ $group: { _id: $country, total : {$sum : 1 } } }")
	List<CountryAggregation> countByCountry();

    //select country, [names] from user group by country
    @Aggregation("{ $group: { _id : $country, names : { $addToSet : $email } } }")
	List<UserAggregation> groupByCountry();
}

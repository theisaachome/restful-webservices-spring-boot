package com.isaachome.entity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "legosets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LegoSet {

	@Id
	private String id;
	private String name;
	private LegoSetDifficulty legoSetDifficulty;
	@Indexed(direction = IndexDirection.ASCENDING)
	private String theme;
	private Collection<ProductReview> productReviews= new ArrayList<>();
	@Field(name = "delivery")
	private DeliveryInfo deliveryInfo;
	@Transient
	private int nbParts;
	
}

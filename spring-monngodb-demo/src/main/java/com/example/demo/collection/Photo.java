package com.example.demo.collection;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Document(collection = "photo")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Photo {

	private String photoId;
	private String title;
	private Binary photo;
}

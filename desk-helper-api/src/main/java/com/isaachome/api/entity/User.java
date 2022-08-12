package com.isaachome.api.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.isaachome.api.enums.ProfileEnum;

import lombok.Data;

@Data
@Document
public class User {

	@Id
	private String id;

	@Indexed(unique=true)
	@NotBlank(message="Email is required")
	@Email(message = "Email invalid")
	private String email;
	
	@NotBlank(message="Password is required")
	@Size(min=6)
	private String password;
	
	private ProfileEnum profile;

}

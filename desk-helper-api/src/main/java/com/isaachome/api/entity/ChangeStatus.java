package com.isaachome.api.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.isaachome.api.enums.StatusEnum;

import lombok.Data;

@Data
@Document
public class ChangeStatus {

	@Id
	private String id;
	
	@DBRef(lazy=true)
	private Ticket ticket;
	
	@DBRef(lazy=true)
	private User userChange;
	
	private LocalDate dateChangeStatus;
	
	private StatusEnum status;

	

}

package com.isaachome.api.repos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.isaachome.api.entity.ChangeStatus;

public interface ChangeStatusRepo extends MongoRepository<ChangeStatus, String> {

	List<ChangeStatus> findByTicketOrderByDateChangeStatus(String ticketId);
}

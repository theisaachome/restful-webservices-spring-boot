package com.isaachome.api.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.isaachome.api.entity.Ticket;

public interface TicketRepo extends MongoRepository<Ticket, String> {

	Page<Ticket> findByUserIdOrderByCreatedAtDesc(Pageable pages, String userid);

	Page<Ticket> findByTitleIgnoreCaseContainingAndStatusAndPriorityOrderByCreatedAtDesc(String title, String status,
			String Priority, Pageable pages);

	Page<Ticket> findByTitleIgnoreCaseContainingAndStatusAndPriorityAndUserIdOrderByCreatedAtDesc(String title,
			String status, String Priority, Pageable pages);

	Page<Ticket> findByTitleIgnoreCaseContainingAndStatusAndPriorityAndAssignedUserIdOrderByCreatedAtDesc(String title,
			String status, String Priority, Pageable pages);

	Page<Ticket> findByNumber(Integer number, Pageable pages);
}

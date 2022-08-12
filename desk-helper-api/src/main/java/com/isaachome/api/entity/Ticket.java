package com.isaachome.api.entity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.isaachome.api.enums.PriorityEnum;
import com.isaachome.api.enums.StatusEnum;
import lombok.Data;

@Document
@Data
public class Ticket {

	@Id
	private String id;
	@DBRef(lazy = true)
	private User user;

	private LocalDate createdAt;

	private String title;

	private Integer number;

	private StatusEnum status;

	private PriorityEnum priority;

	@DBRef(lazy = true)
	private User assignedUser;

	private String description;

	private String image;


	@Transient
	private List<ChangeStatus> changes;
}

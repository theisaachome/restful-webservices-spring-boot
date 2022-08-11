package com.pbc.student;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootApplication
public class StudentAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentAPIApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepo studentRepo,MongoTemplate mongoTemplate) {
		return arg->{
			var address = new Address("England","Liverpool","11061");
			Student isaac = new Student(
					"Isaac",
					"Home",
					"isaachome@gmail.com",
					Gender.MALE,
					address,
					List.of("Computer Science","Biology"),
					BigDecimal.TEN,
					LocalDateTime.now()
					);
			Student olivia = new Student(
					"olivia",
					"couplet",
					"olivia@gmail.com",
					Gender.FEMALE,
					address,
					List.of("Computer Science"),
					BigDecimal.TEN,
					LocalDateTime.now()
					);
//			usingMongoTemplate(studentRepo, mongoTemplate, isaac, olivia);
			studentRepo.findStudentByEmail("olivia@gmail.com")
			.ifPresentOrElse(s->{
				System.out.println(s+ " already exist.");
			}, ()->{
				System.out.println("Inserting Student"+ isaac);
				studentRepo.insert(List.of(isaac,olivia));
			});;
		};
	}

	private void usingMongoTemplate(StudentRepo studentRepo, MongoTemplate mongoTemplate, Student isaac,
			Student olivia) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is("olivia@gmail.com"));

		List<Student> students=mongoTemplate.find(query, Student.class);
		if(students.size() > 1) {
			throw new IllegalStateException("Found more than 1");
		}
		if(students.isEmpty()) {
			System.out.println("Inserting students");
			studentRepo.insert(List.of(isaac,olivia));

		}else {
			System.out.println( students+ "Students already exists");
		}
	}
}

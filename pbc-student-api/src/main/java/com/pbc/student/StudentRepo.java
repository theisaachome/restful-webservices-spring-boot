package com.pbc.student;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo  extends MongoRepository<Student, String>{

	Optional<Student> findStudentByEmail(String email);
}

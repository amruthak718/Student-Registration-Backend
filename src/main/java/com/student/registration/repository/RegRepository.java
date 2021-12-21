package com.student.registration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.student.registration.entity.Student;

@Repository
public interface RegRepository extends MongoRepository<Student, Integer>{

}

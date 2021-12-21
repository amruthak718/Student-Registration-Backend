package com.student.registration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.student.registration.entity.Student;
import com.student.registration.services.RegServices;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api")
public class RegController {
	
	@Autowired
	RegServices services;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveTask(@RequestBody Student student) {
		return services.save(student);
	}
	
	@RequestMapping(value = "/students")
	public List<Student> getAllStudents() {
		return services.getAll();
	}

}

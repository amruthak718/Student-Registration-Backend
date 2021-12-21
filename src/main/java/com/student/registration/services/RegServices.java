package com.student.registration.services;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.student.registration.entity.IdGenerator;
import com.student.registration.entity.Student;
import com.student.registration.repository.RegRepository;

@Service
public class RegServices {
	
	@Autowired
	private RegRepository regRepo;
	
	@Autowired
	private MongoOperations mongoOperations;
	
	public int getIdNumber(String idGenName) {
		//get sequence number
		Query query = new Query(Criteria.where("id").is(idGenName));
		//update the sequence number
		Update update = new Update().inc("idGenerator", 1);
		//modify in the document
		IdGenerator counter = mongoOperations.findAndModify(query,
				update, options().returnNew(true).upsert(true),
				IdGenerator.class);
		return !Objects.isNull(counter)? counter.getIdGenerator() : 1;
	}
	
	public String save(Student student) {
		if(validateInput(student.getName())) {
			int idVal = getIdNumber(Student.IDGEN_NAME);
			if(idVal < 10) {
				student.setId("R-00" + idVal);
			} else if(idVal >= 10 && idVal < 100) {
				student.setId("R-0" + idVal);
			} else {
				student.setId("R-" + idVal);
			}
			regRepo.save(student);
			return "Saved successfully";
		} else {
			return "Validation Error";
		}
	}
	
	public List<Student> getAll() {
		List<Student> studentList = regRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
		return studentList;
	}
	
	public boolean validateInput(String name) {
		return Pattern.matches("^(([A-Za-z]{1,})[\\s]{0,1}){1,}(?<!\\s)$", name);
	}

}

package com.student.registration.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "id_generator")
public class IdGenerator {
	
		@Id
		private String id;
		private int idGenerator;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public int getIdGenerator() {
			return idGenerator;
		}
		public void setIdGenerator(int idGenerator) {
			this.idGenerator = idGenerator;
		}
		
}

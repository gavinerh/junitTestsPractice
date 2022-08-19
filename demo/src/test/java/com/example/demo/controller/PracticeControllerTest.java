package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.example.demo.DemoApplication;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

@SpringBootTest(classes = DemoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PracticeControllerTest {
	@LocalServerPort
	private int port = 8080;
	
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@Autowired
	PersonRepository repo;
		
	
	@BeforeEach
	public void setup() {
		String johnId = UUID.randomUUID().toString(); 
		Person john = new Person(johnId, "john", "john@gmail.com");
		Person tom = new Person(UUID.randomUUID().toString(), "tom", "tom@gmail.com");
		Person thomas = new Person(UUID.randomUUID().toString(), "thomas", "thomas@gmail.com");
		repo.insert(john); repo.insert(thomas); repo.insert(tom);
		
	}
	
	@AfterEach
	public void cleanup() {
		List<Person> personList = repo.findAll();
		for(Person person : personList) {
			repo.deleteById(person.getId());
		}
	}
	
	@Test
	@DisplayName("Test getAll method")
	public void testGetAll() {
		ResponseEntity<Person[]> response = restTemplate.getForEntity("http://localhost:" + port + "/practice/", Person[].class);
		assertEquals(200, response.getStatusCodeValue(), "Should be 200");
		assertEquals(3, response.getBody().length, "Should contain 3 persons");
	}
	
	@Test
	@DisplayName("Test delete by id")
	public void testDeleteById() {
		List<Person> storedPersonList = repo.findAll();
		int originalSize = storedPersonList.size();
		Person person1 = storedPersonList.get(0);
		String id = person1.getId();
		restTemplate.delete("http://localhost:" + port + "/practice/" + id, String.class);
		int afterDeleteOperationPersonList = repo.findAll().size();
		assertEquals(originalSize - 1, afterDeleteOperationPersonList, "Should be the same");
	}
	
	@Test
	@DisplayName("Test get personById")
	public void testGetPersonById() {
		List<Person> personList = repo.findAll();
		Person person = personList.get(1);
		ResponseEntity<Person> okResponse = restTemplate.getForEntity("http://localhost:" + port + "/practice/" + person.getId(), Person.class);
		assertNotNull(okResponse.getBody());
		assertEquals(200, okResponse.getStatusCodeValue(), "Should be 200");
		
		ResponseEntity<Person> errorResponse = restTemplate.getForEntity("http://localhost:" + port + "/practice/" + "123", Person.class);
		assertEquals(404, errorResponse.getStatusCodeValue());
		assertNull(errorResponse.getBody());
 	}
	
	@Test
	@DisplayName("Test createPerson")
	public void testCreatePerson() {
		Person john = new Person();
		john.setEmail("john@gmail.com"); john.setName("john");
		ResponseEntity<Person> response = restTemplate.postForEntity("http://localhost:" + port + "/practice/", john, Person.class);
		assertEquals(201, response.getStatusCodeValue(), "Status code be ok 201");
		assertNotNull(response.getBody());
	}
	
}

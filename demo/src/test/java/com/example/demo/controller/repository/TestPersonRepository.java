//package com.example.demo.controller.repository;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.example.demo.model.Person;
//import com.example.demo.repository.PersonRepository;
//
//@RunWith(SpringRunner.class)
//public class TestPersonRepository {
//	
//	@MockBean
//	PersonRepository repo;
//	
//	private String personid;
//	
//	@BeforeEach
//	public void setup() {
//		String gavinId = UUID.randomUUID().toString();
//		Person gavin = new Person(gavinId, "gavin", "gavinerh@gmail.com");
//		List<Person> personList = new ArrayList<>();
//		personList.add(gavin);
//		Mockito.when(repo.findByName("gavin")).thenReturn(gavin);
//		Mockito.when(repo.findById(gavinId)).thenReturn(gavin);
//		Mockito.when(repo.findAll()).thenReturn(personList);
//		Mockito.when(repo.insert(gavin)).thenReturn(true);
//		
//	}
//	
//	@Test
//	@DisplayName("Test find all when the repo is initially empty")
//	public void testFindAll() {
//		List<Person> personList = repo.findAll();
//		assertEquals(1, personList.size(), "Should only contain 1");
//	}
//}

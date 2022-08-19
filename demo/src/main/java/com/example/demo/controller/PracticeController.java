package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

@RestController
@RequestMapping("/practice")
public class PracticeController {
	
	@Autowired
	private PersonRepository repo;
	
	public PracticeController(PersonRepository repo) {
		this.repo = repo;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Person>> getAll(){
		List<Person> persons = repo.findAll();
		return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable String id){
		Person p = repo.findById(id);
		if(p == null) {
			return new ResponseEntity<Person>(p, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(p, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Person> createPerson(@RequestBody Person person){
		person.setId(UUID.randomUUID().toString());
		repo.insert(person);
		return new ResponseEntity<Person>(person, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id){
		System.out.println(repo.deleteById(id));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}

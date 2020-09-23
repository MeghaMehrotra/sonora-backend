package com.assignment.sonora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.sonora.dto.MessageDTO;
import com.assignment.sonora.entity.Person;
import com.assignment.sonora.service.interf.IPersonService;

@RestController
@RequestMapping("/api/People")
public class PersonController {

	@Autowired
	private IPersonService personService;

	/**
	 * 
	 * Method to get List of Persons
	 */
	@GetMapping
	public ResponseEntity<List<Person>> getPersons() throws Exception{
		List<Person> Persons = personService.getAllPersons();
		return new ResponseEntity<>(Persons, HttpStatus.OK);
	}

	/**
	 * 
	 * @param userAgent
	 * @param personId
	 * @param person
	 * @return Person
	 */
	@PatchMapping("/{personId}")
	public ResponseEntity<MessageDTO<Person>> updatePerson(@RequestHeader("User-Agent") String userAgent,
			@PathVariable("personId") Long personId, @RequestBody Person person) throws Exception {
		if (userAgent.equals("googlebot")) {
			if (person != null && personId != null) {
				person.setId(personId);
				person = personService.save(person);
			}
			return new ResponseEntity<>(new MessageDTO<>("Updated Person Successfully!!", person, true), HttpStatus.OK);
		} else
			return new ResponseEntity<>(new MessageDTO<>("Error in updating", false), HttpStatus.OK);
	}

	/**
	 * 
	 * @param userAgent
	 * @param person
	 * @return Person
	 */
	@PostMapping
	public ResponseEntity<MessageDTO<Person>> savePerson(@RequestHeader("User-Agent") String userAgent,
			@RequestBody Person person) throws Exception {
		if (userAgent.contentEquals("googlebot")) {
			if (person != null) {
				person = personService.save(person);
			}
			return new ResponseEntity<>(new MessageDTO<>("Person save success!!", person, true), HttpStatus.CREATED);

		} else {
			return new ResponseEntity<>(new MessageDTO<>("Error in saving", false), HttpStatus.OK);
		}

	}

	/**
	 * 
	 * @param personId
	 * @return Person
	 */
	@GetMapping("/{personId}")
	public ResponseEntity<MessageDTO<Person>> getPerson(@PathVariable("personId") Long personId) throws Exception {
		if (personId != null) {
			Person person = personService.getPerson(personId);
			return new ResponseEntity<>(new MessageDTO<>("Updated Person Successfully!!", person, true), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new MessageDTO<>("Error in fetching", false), HttpStatus.OK);
		}
	}

}

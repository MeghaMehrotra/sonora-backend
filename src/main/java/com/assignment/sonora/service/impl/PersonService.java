package com.assignment.sonora.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.sonora.entity.Person;
import com.assignment.sonora.repository.IPersonRepository;
import com.assignment.sonora.service.interf.IPersonService;

/**
 * 
 * @author Megha
 */

@Service
public class PersonService implements IPersonService {

	@Autowired
	private IPersonRepository personRepo;

	@Override
	public List<Person> getAllPersons() {
		return personRepo.findAll();
	}

	@Override
	public Person save(Person person) {
		return personRepo.save(person);
	}

	@Override
	public Person getPerson(Long personId) {
		return personRepo.getOne(personId);
	}
}

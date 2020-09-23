package com.assignment.sonora.service.interf;

import java.util.List;

import com.assignment.sonora.entity.Person;

/**
 * 
 * @author Megha
 *
 */
public interface IPersonService {

	List<Person> getAllPersons();

	Person save(Person person);

	Person getPerson(Long personId);
}

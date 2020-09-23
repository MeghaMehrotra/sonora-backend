package com.assignment.sonora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.sonora.entity.Person;

/**
 * 
 * @author Megha
 *
 */

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {

}

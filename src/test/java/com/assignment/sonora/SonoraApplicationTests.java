package com.assignment.sonora;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.assignment.sonora.entity.Person;
import com.assignment.sonora.service.impl.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = SonoraApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
class SonoraApplicationTests {

	@MockBean
	PersonService personService;

	@MockBean
	Person person;

	@Autowired
	private MockMvc mockMvc;

	@Value("${person.name}")
	private String name;

	@Value("${person.email}")
	private String email;

	@Value("${person.dob}")
	private String dob;

	@Value("${person.avatar}")
	private String avatar;

	@Value("${person.country}")
	private String country;

	@Value("${request.url}")
	private String reqUrl;

	@Test
	void savePersonController() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("User-Agent", "googlebot");

		Person testPerson = returnPerson();

		Mockito.when(personService.save(person)).thenReturn(testPerson);

		mockMvc.perform(post(reqUrl + "/api/People").headers(httpHeaders)
				.content(objectMapper.writeValueAsString(testPerson)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

	}

	@Test
	void updatePersonController() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("User-Agent", "googlebot");

		Person testPerson = returnPerson();

		Mockito.when(personService.save(person)).thenReturn(testPerson);

		mockMvc.perform(patch(reqUrl + "/api/People/1").headers(httpHeaders)
				.content(objectMapper.writeValueAsString(testPerson)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void getPersonController() throws Exception {

		Person testPerson = returnPerson();

		Mockito.when(personService.getPerson(Mockito.anyLong())).thenReturn(testPerson);

		mockMvc.perform(get(reqUrl + "/api/People/1")).andExpect(status().isOk());

	}

	@Test
	void getAllPersons() throws Exception {
		List<Person> persons = new ArrayList<Person>();
		Person testPerson = returnPerson();
		persons.add(testPerson);

		Mockito.when(personService.getAllPersons()).thenReturn(persons);

		mockMvc.perform(get(reqUrl + "/api/People")).andExpect(status().isOk());

	}

	Person returnPerson() {
		Person testPerson = new Person();
		testPerson.setId(1L);
		testPerson.setName(name);
		testPerson.setDob(dob);
		testPerson.setCountry(country);
		testPerson.setEmail(email);
		testPerson.setAvatar(avatar);
		return testPerson;

	}

}

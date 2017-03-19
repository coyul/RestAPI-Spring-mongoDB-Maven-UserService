package serverApp.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import serverApp.Model.Person;
import serverApp.Repository.PersonRepository;

@RestController
@RequestMapping("/person")
public class PersonController {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

	@Autowired
	private PersonRepository personRepository;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// create new person with unique email and encrypted password
	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> createPerson(@RequestBody Map<String, Object> personMap) throws ParseException {
		Person person = new Person(personMap.get("name").toString(), personMap.get("surname").toString(), dateFormat.parse(personMap.get("birthDate").toString()), personMap.get("email").toString(), personMap.get("password").toString());
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		for (Person p : personRepository.findAll()) {
			if (p.getEmail().equals(person.getEmail())) {
				response.put("message", "Person with such email already exists");
				return response;
			}
		}
		person.setPassword(bCryptPasswordEncoder().encode(person.getPassword()));

		response.put("message", "New person created successfully");
		response.put("person", personRepository.save(person));
		return response;
	}

	// get person by email
	@RequestMapping(method = RequestMethod.GET, value = "/email='{email}'")
	public Map<String, Object> getPerson(@PathVariable("email") String email) {
		String id = null;
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		for (Person person : personRepository.findAll()) {
			if (person.getEmail().equals(email))
				id = person.getId();
		}
		if (id != null)
			response.put("Person is found", personRepository.findOne(id));
		else
			response.put("Person has not been found", null);

		return response;

	}

	// delete person by id
	@RequestMapping(method = RequestMethod.DELETE, value = "/{personId}")
	public Map<String, Object> deletePerson(@PathVariable("personId") String personId) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (personRepository.findOne(personId) == null) {
			response.put("message", "No person to delete");
			return response;
		} else {
			personRepository.delete(personId);
			response.put("message", "Person deleted successfully");
			return response;
		}
	}

	// getting by id
	// @RequestMapping(method = RequestMethod.GET, value = "/{personId}")
	// public Person getBookDetails(@PathVariable("personId") String personId) {
	// return personRepository.findOne(personId);
	// }

	// getting all persons
	// @RequestMapping(method = RequestMethod.GET)
	// public Map<String, Object> getAllPersons() {
	// List<Person> person = personRepository.findAll();
	// Map<String, Object> response = new LinkedHashMap<String, Object>();
	// response.put("totalPersons", person.size());
	// response.put("persons", person);
	// return response;
	// }
}

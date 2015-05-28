package pl.proacem.service;

import org.springframework.stereotype.Service;

import pl.proacem.model.Person;

@Service
public interface PersonServiceInterface extends ServiceInterface<Person> {
	public Person getLogin(Person person);

}

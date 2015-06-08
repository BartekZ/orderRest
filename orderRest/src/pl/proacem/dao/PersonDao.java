package pl.proacem.dao;

import java.util.List;

import pl.proacem.model.Person;

public interface PersonDao extends Dao<Person>{
	public Person findByLogin(String login);
	public List<Person> findByPhone(String phone);
	public Person findLoginPass(Person person);
	
}

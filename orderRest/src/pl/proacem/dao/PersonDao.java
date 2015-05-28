package pl.proacem.dao;

import pl.proacem.model.Person;

public interface PersonDao extends Dao<Person>{
	public Person findByLogin(String login);
	public Person findByPhone(String phone);
	public Person findLoginPass(Person person);
	
}

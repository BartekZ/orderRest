package pl.proacem.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import pl.proacem.dao.PersonDao;
import pl.proacem.model.Person;
import pl.proacem.model.SingleOrder;

@Service(value="PersonService")
@Transactional
public class PersonService implements PersonServiceInterface{

	@Autowired
	private PersonDao personDao;
	
	public Person getById(int id) {
		return personDao.getById(id);
	}

	public void add(Person person){
		personDao.add(person);
	}

	public void deleteById(int id) {
		personDao.deleteById(id);
	}

	public void update(Person person) {
		personDao.update(person);
	}
	
	public List<Person> getAll() {
		return personDao.getAll();
	}
	
	public Person getLastId(){
		return personDao.getLastId();
	}

	public Person getLogin(Person person){
		return personDao.findLoginPass(person);
	}
	
}

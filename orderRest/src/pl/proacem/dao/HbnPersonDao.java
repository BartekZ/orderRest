package pl.proacem.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import pl.proacem.model.Person;
import pl.proacem.model.SingleOrder;

@Repository
@Transactional
public class HbnPersonDao implements PersonDao {

	
	public HbnPersonDao() {
	}

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void add(Person item) {
		item.setCreated(new Date());
		item.setUpdated(new Date());
		em.persist(item);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getAll() {
		List<Person> PersonListAll = new ArrayList<Person>();
		PersonListAll = em.createQuery("from Person").getResultList();
		return PersonListAll;
	}

	@Override
	public Person getById(int id) {
		Person person = (Person) em.createQuery("from Person p where p.id = :id").setParameter("id", id).getSingleResult();
		if (person != null){
			return person;
		}
		return null;
	}

	@Override
	public void delete(Person item) {
		em.remove(item);
	}

	@Override
	public void deleteById(int id) {
		em.remove(getById(id));

	}

	@Override
	public void update(Person item) {
		item.setUpdated(new Date());
		em.merge(item);

	}

	@Override
	public Person findByLogin(String login) {
		return (Person) em.createQuery("from Person p where p.login = :login").setParameter("login", login).setMaxResults(1).getSingleResult();
		
	}

	@Override
	public List<Person> findByPhone(String phone) {
		List<Person> List = new ArrayList<Person>();
		try {
			List = em.createQuery("from Person p WHERE p.phone LIKE :phone").setParameter("phone", "%" +phone + "%").getResultList();
			return List;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Person> findByStatus(int status) {
		return em.createQuery("from Person p where p.status = :status").setParameter("status", status).getResultList();
		
	}

	public Person getLastId(){
		Person person = (Person) em.createQuery("from Person p order by p.id desc").setMaxResults(1).getSingleResult();
		return person;
	}

	@Override
	public Person findLoginPass(Person person) {
		Person person2 = null;
		try {
			person2 = (Person) em.createQuery("from Person p where p.login = :login and p.pass = :pass").setParameter("login", person.getLogin()).setParameter("pass", person.getPass()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		catch (NonUniqueResultException e){
			return null;
		}
		catch (Exception e){
			return null;
		}
		return person2;
		
		
	}
}

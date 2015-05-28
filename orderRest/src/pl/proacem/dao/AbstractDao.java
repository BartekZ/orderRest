package pl.proacem.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.proacem.model.ModelInterface;

public abstract class AbstractDao<T extends ModelInterface> implements Dao<T> {

	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public void add(T item) {
		item.setCreated(new Date());
		item.setUpdated(new Date());
		em.persist(item);
	}

	@Override
	public void delete(T item) {
		em.remove(item);
		
	}

	@Override
	public void update(T item) {
		item.setUpdated(new Date());
		em.merge(item);
	}




	
}

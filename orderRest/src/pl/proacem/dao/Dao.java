package pl.proacem.dao;

import java.util.List;

import pl.proacem.model.ModelInterface;

public abstract interface Dao<T extends ModelInterface> {
	
	public void add(T item);
	public List<T> getAll();
	public T getById(int id);
	public void delete(T item);
	public void deleteById(int id);
	public void update(T item);
	public List<T> findByStatus(int status);
	public T getLastId();
	
}

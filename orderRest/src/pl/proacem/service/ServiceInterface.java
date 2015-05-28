package pl.proacem.service;

import java.util.List;

public interface ServiceInterface<T> {

	T getById(int id);

	void add(T item);

	void deleteById(int id);

	void update(T item);

	List<T> getAll();
	
	T getLastId();

	//List<SingleOrder> getByMainOrder(int id);
	
	

}
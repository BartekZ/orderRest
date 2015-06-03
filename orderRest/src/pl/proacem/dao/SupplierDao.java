package pl.proacem.dao;

import java.util.List;

import pl.proacem.model.Supplier;

public interface SupplierDao extends Dao<Supplier> {
	public List<Supplier> findByName(String word);
	public List<Supplier> findByDescription(String word);
	public List<Supplier> findByAddress(String word);
	public List<Supplier> findByPhone(String word);
	
	
}

package pl.proacem.dao;

import pl.proacem.model.Supplier;

public interface SupplierDao extends Dao<Supplier> {
	public Supplier findByAddress();
	public Supplier findByPhone();
	
}

package pl.proacem.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.proacem.dao.SupplierDao;
import pl.proacem.model.Supplier;

@Service(value="SupplierService")
@Transactional
public class SupplierService implements ServiceInterface<Supplier>{

	@Autowired
	private SupplierDao supplierDao;
	
	public Supplier getById(int id) {
		return supplierDao.getById(id);
	}

	public void add(Supplier supplier) {
		supplierDao.add(supplier);
	}

	public void update(Supplier supplier) {
		supplierDao.update(supplier);
		
	}

	public void deleteById(Integer id) {
		supplierDao.deleteById(id);
		
	}

	public List<Supplier> getAll() {
		return supplierDao.getAll();
	}

	@Override
	public void deleteById(int id) {
		supplierDao.deleteById(id);
		
	}

	@Override
	public Supplier getLastId() {
		return supplierDao.getLastId();
	}

	public List<Supplier> findByName(String word){
		return supplierDao.findByName(word);
	}
	public List<Supplier> findByDescription(String word){
		return supplierDao.findByDescription(word);
	}
	
	public List<Supplier> findByAddress(String word){
		return supplierDao.findByAddress(word);
	}
	
	public List<Supplier> findByPhone(String word){
		return supplierDao.findByPhone(word);
	}


	
}

package pl.proacem.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import pl.proacem.model.Supplier;

@Repository
@Transactional
public class HbnSupplierDao extends AbstractDao<Supplier> implements SupplierDao {

	@Override
	public void add(Supplier item) {
		em.persist(item);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Supplier> getAll() {
		List<Supplier> SupplierListAll = new ArrayList<Supplier>();
		SupplierListAll = em.createQuery("from Supplier").getResultList();
		return SupplierListAll;
	}

	@Override
	public Supplier getById(int id) {
		Supplier supplier = (Supplier) em.createQuery("from Supplier p where p.id = :id").setParameter("id", id).getSingleResult();
		if (supplier != null){
			return supplier;
		}
		return null;
	}

	@Override
	public void delete(Supplier item) {
		em.remove(item);

	}

	@Override
	public void deleteById(int id) {
		em.remove(em.getReference(Supplier.class, id));

	}

	@Override
	public void update(Supplier item) {
		em.merge(item);
		
	}

	@Override
	public List<Supplier> find(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Supplier> findByStatus(int status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Supplier findByAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Supplier findByPhone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Supplier getLastId(){
		Supplier item = (Supplier) em.createQuery("from Supplier p order by p.id desc").setMaxResults(1).getSingleResult();
		return item;
	}

}

package pl.proacem.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import pl.proacem.model.MainOrder;
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
	public List<Supplier> findByStatus(int status) {
		return em.createQuery("from Supplier s where s.status = :status").setParameter("status", status).getResultList();
	}

	
	public Supplier getLastId(){
		Supplier item = (Supplier) em.createQuery("from Supplier p order by p.id desc").setMaxResults(1).getSingleResult();
		return item;
	}

	@Override
	public List<Supplier> findByName(String word) {
		List<Supplier> list = new ArrayList<Supplier>();
		try {
			list = em.createQuery("from Supplier s WHERE s.name LIKE :search").setParameter("search", "%" +word + "%").getResultList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Supplier> findByDescription(String word) {
		List<Supplier> list = new ArrayList<Supplier>();
		try {
			list = em.createQuery("from Supplier s WHERE s.description LIKE :search").setParameter("search", "%" +word + "%").getResultList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Supplier> findByAddress(String word) {
		List<Supplier> list = new ArrayList<Supplier>();
		try {
			list = em.createQuery("from Supplier s WHERE s.address LIKE :search").setParameter("search", "%" +word + "%").getResultList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Supplier> findByPhone(String word) {
		List<Supplier> list = new ArrayList<Supplier>();
		try {
			list = em.createQuery("from Supplier s WHERE s.phone LIKE :search").setParameter("search", "%" +word + "%").getResultList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

}

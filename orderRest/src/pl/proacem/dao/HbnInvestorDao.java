package pl.proacem.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import pl.proacem.model.Investor;
import pl.proacem.model.SingleOrder;
@Repository
@Transactional
public class HbnInvestorDao extends AbstractDao<Investor> implements InvestorDao{

	
	
	@Override
	public void deleteById(int id) {
		em.remove(em.getReference(Investor.class, id));
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Investor> findByStatus(int status) {
		return em.createQuery("from Investor i where i.status = :status").setParameter("status", status).getResultList();
	}

	@Override
	public Investor getById(int id) {
		Investor investor = (Investor) em.createQuery("from Investor p where p.id = :id").setParameter("id", id).getSingleResult();
		if (investor != null){
			return investor;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Investor> getAll() {
		List<Investor> ListAll = new ArrayList<Investor>();
		ListAll = em.createQuery("from Investor").getResultList();
		return ListAll;
	}

	public Investor getLastId(){
		Investor item = (Investor) em.createQuery("from Investor p order by p.id desc").setMaxResults(1).getSingleResult();
		return item;
	}

	@Override
	public List<Investor> findByDescription(String word) {
		List<Investor> List = new ArrayList<Investor>();
		try {
			List = em.createQuery("from Investor s WHERE s.description LIKE :search").setParameter("search", "%" +word + "%").getResultList();
			return List;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Investor> findByNote(String word) {
		List<Investor> List = new ArrayList<Investor>();
		try {
			List = em.createQuery("from Investor s WHERE s.note LIKE :search").setParameter("search", "%" +word + "%").getResultList();
			return List;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Investor> findByName(String word) {
		List<Investor> List = new ArrayList<Investor>();
		try {
			List = em.createQuery("from Investor s WHERE s.name LIKE :search").setParameter("search", "%" +word + "%").getResultList();
			return List;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

}

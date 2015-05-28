package pl.proacem.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import pl.proacem.model.Investor;
@Repository
@Transactional
public class HbnInvestorDao extends AbstractDao<Investor> implements InvestorDao{

	
	
	@Override
	public void deleteById(int id) {
		em.remove(em.getReference(Investor.class, id));
	}

	@Override
	public List<Investor> find(String text) {
		// TODO Auto-generated method stub
		return null;
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
	

}

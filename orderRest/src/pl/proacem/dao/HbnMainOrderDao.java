package pl.proacem.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import pl.proacem.model.MainOrder;

@Repository
@Transactional
public class HbnMainOrderDao extends AbstractDao<MainOrder> implements MainOrderDao{

	@Override
	public MainOrder getById(int id) {
		MainOrder item = (MainOrder) em.createQuery("from MainOrder p where p.id = :id").setParameter("id", id).getSingleResult();
		if (item != null){
			return item;
		}
		return null;
	}

	@Override
	public void deleteById(int id) {
		em.remove(em.getReference(MainOrder.class, id));
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MainOrder> findByStatus(int status) {
		
		return em.createQuery("from Mainorder m where m.status = :status").setParameter("status", status).getResultList();
	}

	@Override
	public List<MainOrder> findByLeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MainOrder> findByInvestor() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MainOrder> getAll() {
		List<MainOrder> ListAll = new ArrayList<MainOrder>();
		ListAll = em.createQuery("from MainOrder").getResultList();
		return ListAll;
	}
	
	public MainOrder getLastId(){
		MainOrder item = (MainOrder) em.createQuery("from MainOrder p order by p.id desc").setMaxResults(1).getSingleResult();
		return item;
	}

	@Override
	public List<MainOrder> findByMainNumber(String word) {
		List<MainOrder> list = new ArrayList<MainOrder>();
		try {
			list = em.createQuery("from Mainorder s WHERE s.mainNumber LIKE :search").setParameter("search", "%" +word + "%").getResultList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<MainOrder> findByTopic(String word) {
		List<MainOrder> list = new ArrayList<MainOrder>();
		try {
			list = em.createQuery("from Mainorder s WHERE s.topic LIKE :search").setParameter("search", "%" +word + "%").getResultList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

}

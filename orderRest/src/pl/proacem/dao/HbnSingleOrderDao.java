package pl.proacem.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import pl.proacem.model.MainOrder;
import pl.proacem.model.SingleOrder;

@Repository
@Transactional
public class HbnSingleOrderDao extends AbstractDao<SingleOrder> implements SingleOrderDao {

	@Override
	public SingleOrder getById(int id) {
		SingleOrder singleorder = (SingleOrder) em.createQuery("from SingleOrder p where p.id = :id").setParameter("id", id).getSingleResult();
		if (singleorder != null){
			return singleorder;
		}
		return null;
	}

	@Override
	public void deleteById(int id) {
		em.remove(em.getReference(SingleOrder.class, id));
		
	}

	@Override
	public List<SingleOrder> find(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SingleOrder> findByStatus(int status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SingleOrder> findByOrderNumber(String searchPhase) {
		List<SingleOrder> List = new ArrayList<SingleOrder>();
		//List = em.createQuery("from SingleOrders s WHERE s.OrderName LIKE :search").setParameter("search", Integer.parseInt(searchPhase)).getResultList();
		List = em.createQuery("from SingleOrders s WHERE s.OrderName LIKE 452").getResultList();
				
				return List;
	}

	@Override
	public List<SingleOrder> findByOfferNumber(String searchPhase) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SingleOrder> findByValuePln(String searchPhase) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SingleOrder> findByValueEur(String searchPhase) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SingleOrder> findBySupplier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SingleOrder> findByContractor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SingleOrder> findByMainOrder() {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SingleOrder> getAll() {
		List<SingleOrder> ListAll = new ArrayList<SingleOrder>();
		ListAll = em.createQuery("from SingleOrder").getResultList();
		return ListAll;
	}
	
	public SingleOrder getLastId(){
		SingleOrder item = (SingleOrder) em.createQuery("from SingleOrder p order by p.id desc").setMaxResults(1).getSingleResult();
		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SingleOrder> getByMainOrder(MainOrder mainOrder) {
		List<SingleOrder> ListAll = new ArrayList<SingleOrder>();
		ListAll = em.createQuery("from SingleOrder s where s.owner = :id").setParameter("id", mainOrder).getResultList();
		return ListAll;
	}

}

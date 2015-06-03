package pl.proacem.dao;

import java.util.List;

import pl.proacem.model.MainOrder;
import pl.proacem.model.SingleOrder;

public interface SingleOrderDao extends Dao<SingleOrder> {
	public List<SingleOrder> findByOrderNumber(String searchPhase);
	public List<SingleOrder> findByOfferNumber(String searchPhase);
	public List<SingleOrder> findBySpecification(String searchPhase);
	public List<SingleOrder> findByQuantity(String searchPhase);
	public List<SingleOrder> findByOther(String searchPhase);
	
	
	public List<SingleOrder> findByValuePln(String searchPhase);
	public List<SingleOrder> findByValueEur(String searchPhase);
		
	public List<SingleOrder> findBySupplier();
	public List<SingleOrder> findByContractor();
	public List<SingleOrder> findByMainOrder();
	public List<SingleOrder> getByMainOrder(MainOrder mainOrder);
	
	
	
	
	
	
}

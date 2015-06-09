package pl.proacem.dao;

import java.util.List;

import pl.proacem.model.MainOrder;
import pl.proacem.model.Person;
import pl.proacem.model.SingleOrder;
import pl.proacem.model.Supplier;

public interface SingleOrderDao extends Dao<SingleOrder> {
	public List<SingleOrder> findByOrderNumber(String searchPhase);
	public List<SingleOrder> findByOfferNumber(String searchPhase);
	public List<SingleOrder> findBySpecification(String searchPhase);
	public List<SingleOrder> findByQuantity(String searchPhase);
	public List<SingleOrder> findByOther(String searchPhase);
	
	
	public List<SingleOrder> findByValuePln(String searchPhase);
	public List<SingleOrder> findByValueEur(String searchPhase);
		
	public List<SingleOrder> findBySupplier(Supplier supplier);
	public List<SingleOrder> findByContractor(Person contractor);
	public List<SingleOrder> findByMainOrder(MainOrder mainorder);
	public List<SingleOrder> getByMainOrder(MainOrder mainOrder);
	
	
	
	
	
	
}

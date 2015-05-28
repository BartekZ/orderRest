package pl.proacem.dao;

import java.util.List;

import pl.proacem.model.MainOrder;
import pl.proacem.model.SingleOrder;

public interface SingleOrderDao extends Dao<SingleOrder> {
	public SingleOrderDao findByOrderNumber();
	public SingleOrderDao findByOfferNumber();
	public SingleOrderDao findByValuePln();
	public SingleOrderDao findByValueEur();
		
	public List<SingleOrder> findBySupplier();
	public List<SingleOrder> findByContractor();
	public List<SingleOrder> findByMainOrder();
	public List<SingleOrder> getByMainOrder(MainOrder mainOrder);
	
	
	
	
	
}

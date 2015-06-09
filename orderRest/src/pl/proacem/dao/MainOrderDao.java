package pl.proacem.dao;

import java.util.List;

import pl.proacem.model.Investor;
import pl.proacem.model.MainOrder;
import pl.proacem.model.Person;

public interface MainOrderDao extends Dao<MainOrder>{
	public List<MainOrder> findByLeader(Person leader);
	public List<MainOrder> findByInvestor(Investor investor);
	public List<MainOrder> findByMainNumber(String word);
	public List<MainOrder> findByTopic(String word);
	
	
	
}

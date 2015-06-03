package pl.proacem.dao;

import java.util.List;

import pl.proacem.model.MainOrder;

public interface MainOrderDao extends Dao<MainOrder>{
	public MainOrder findByMainNumber();
	public MainOrder findByTopic();
	public List<MainOrder> findByLeader();
	public List<MainOrder> findByInvestor();
	public List<MainOrder> findByMainNumber(String word);
	public List<MainOrder> findByTopic(String word);
	
	
	
}

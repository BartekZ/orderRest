package pl.proacem.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.proacem.dao.MainOrderDao;
import pl.proacem.dao.SingleOrderDao;
import pl.proacem.model.SingleOrder;

@Service(value="SingleOrderService")
@Transactional
public class SingleOrderService implements ServiceInterface<SingleOrder>{

	@Autowired
	private SingleOrderDao singleOrderDao;
	
	@Autowired
	private MainOrderDao mainOrderDao;

	public SingleOrder getById(int id) {
		return singleOrderDao.getById(id);
	}

	public void add(SingleOrder singleorder) {
		singleOrderDao.add(singleorder);
		
	}

	public void update(SingleOrder singleorder) {
		singleOrderDao.update(singleorder);
		
	}

	public void deleteById(Integer id) {
		singleOrderDao.deleteById(id);
		
	}

	public List<SingleOrder> getAll() {
		
		return singleOrderDao.getAll();
	}
	
	public List<SingleOrder> getByMainOrder(int id) {
		
		return singleOrderDao.getByMainOrder(mainOrderDao.getById(id));
	}

	@Override
	public void deleteById(int id) {
		singleOrderDao.deleteById(id);
		
	}

	@Override
	public SingleOrder getLastId() {
		
		return singleOrderDao.getLastId();
	}
	
	
	public  List<SingleOrder> findByOrderNumber(String word) {
		try {
			return singleOrderDao.findByOrderNumber(word);
		} catch (Exception e) {
			return null;
		}
	}
	
	public  List<SingleOrder> findByOfferNumber(String word) {
		try {
			return singleOrderDao.findByOfferNumber(word);
		} catch (Exception e) {
			return null;
		}
	}
	public  List<SingleOrder> findBySpecification(String word) {
		try {
			return singleOrderDao.findBySpecification(word);
		} catch (Exception e) {
			return null;
		}
	}
	
	public  List<SingleOrder> findByOther(String word) {
		try {
			return singleOrderDao.findByOther(word);
		} catch (Exception e) {
			return null;
		}
	}
	
}

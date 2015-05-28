package pl.proacem.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.proacem.dao.MainOrderDao;
import pl.proacem.model.MainOrder;
import pl.proacem.model.SingleOrder;

@Service(value="MainOrderService")
@Transactional
public class MainOrderService implements ServiceInterface<MainOrder>{

	@Autowired
	private MainOrderDao mainOrderDao;
	
	public MainOrder getById(int id) {
		return mainOrderDao.getById(id);
	}

	public void update(MainOrder mainOrder) {
		mainOrderDao.update(mainOrder);
		
	}

	public void deleteById(Integer id) {
		mainOrderDao.deleteById(id);
		
	}

	public void add(MainOrder mainOrder) {
		mainOrderDao.add(mainOrder);
		
	}

	public List<MainOrder> getAll() {
		
		return mainOrderDao.getAll();
	}

	@Override
	public void deleteById(int id) {
		mainOrderDao.deleteById(id);
		
	}

	@Override
	public MainOrder getLastId() {
		return mainOrderDao.getLastId();
	}


}

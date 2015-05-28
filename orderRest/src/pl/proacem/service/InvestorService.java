package pl.proacem.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.proacem.dao.InvestorDao;
import pl.proacem.model.Investor;
import pl.proacem.model.SingleOrder;


@Service(value="InvestorService")
@Transactional
public class InvestorService implements ServiceInterface<Investor>{

	public InvestorService() {
	}

	@Autowired
	private InvestorDao investorDao;
	
	public Investor getById(int id) {
		return investorDao.getById(id);
	}

	public void update(Investor investor) {
		investorDao.update(investor);
		
	}

	public void deleteById(Integer id) {
		investorDao.deleteById(id);
		
	}

	public void add(Investor investor) {
		investorDao.add(investor);
		
	}

	public List<Investor> getAll() {
		
		return investorDao.getAll();
	}

	
	public void deleteById(int id) {
		investorDao.deleteById(id);
		
	}

	@Override
	public Investor getLastId() {
		
		return investorDao.getLastId();
	}

}

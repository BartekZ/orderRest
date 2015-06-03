package pl.proacem.dao;

import java.util.List;

import pl.proacem.model.Investor;

public interface InvestorDao extends Dao<Investor>{

	List<Investor> findByDescription(String word);

	List<Investor> findByNote(String word);

	List<Investor> findByName(String word);

}

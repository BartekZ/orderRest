package pl.proacem.RESTwrong;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.proacem.model.Investor;
import pl.proacem.service.InvestorService;


public class InvestorREST extends AbstractREST<Investor>{

	@Autowired
	InvestorService investorService;
	
	public InvestorREST() {
		this.service = investorService;
	}

	@RequestMapping(
			value="/investor/{id}", 
			method = RequestMethod.GET )
	public Investor itemGET(@PathVariable Integer id) {
		return super.itemGET(id);
	}

	@Override
	@RequestMapping(
			value="/investor", 
			method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Investor itemPOST(Investor item, HttpServletResponse response) {
		return super.itemPOST(item, response);
	}

	@Override
	@RequestMapping(
			value="/investor", 
			method = RequestMethod.PUT )
	public Investor itemPUT(Investor item) {
		return super.itemPUT(item);
	}

	@Override
	@RequestMapping(
			value="/investor/{id}", 
			method = RequestMethod.DELETE )
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void itemDELETE(@PathVariable Integer id) {
		super.itemDELETE(id);
	}

	@Override
	@RequestMapping(
			value="/investor/all", 
			method = RequestMethod.GET )
	public List<Investor> itemsGET() {
		return super.itemsGET();
	}

	@Override
	@RequestMapping(
			value="/investor/from/{from}", 
			method = RequestMethod.GET )
	public List<Investor> fromGET(@PathVariable Integer from, HttpServletResponse response) {
		return super.fromGET(from, response);
	}

	@Override
	@RequestMapping(
			value="/investor/to/{to}", 
			method = RequestMethod.GET )
	public List<Investor> toGET(@PathVariable Integer to, HttpServletResponse response) {
		return super.toGET(to, response);
	}

	@Override
	@RequestMapping(
			value="/investor/from/{from}/to/{to}", 
			method = RequestMethod.GET )
	public List<Investor> fromtoGET(@PathVariable Integer from,@PathVariable Integer to, HttpServletResponse response) {
		return super.fromtoGET(from, to, response);
	}

	@Override
	@RequestMapping(
			value="/investor/size", 
			method = RequestMethod.GET )
	public Integer sizeGET() {
		return super.sizeGET();
	}

	
	
}

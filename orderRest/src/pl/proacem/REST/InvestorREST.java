package pl.proacem.REST;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.proacem.model.Investor;
import pl.proacem.service.InvestorService;
@Secured("ROLE_USER")
@Controller
public class InvestorREST {
	
	@Autowired
	@Qualifier("InvestorService")
	private InvestorService investorService;
	
	
	@RequestMapping(
			value="/investor/{id}", 
			method = RequestMethod.GET )
	public @ResponseBody Investor investorGET(@PathVariable Integer id){
		
		return investorService.getById(id);
	}
	
	
	@RequestMapping(
			value="/investor", 
			method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Investor itemPOST(@RequestBody  Investor investor, HttpServletResponse response){
		investorService.add(investor);
		
		
		response.setHeader("Location", "/investor/" + investor.getId());
		
		return investor;

	}

	
	@RequestMapping(
			value="/investor", 
			method = RequestMethod.PUT )
	public @ResponseBody Investor investorPUT(@RequestBody Investor investor){
		
		investorService.update(investor);
		
		return investor;
	}
	
	
	@RequestMapping(
			value="/investor/{id}", 
			method = RequestMethod.DELETE )
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void investorDELETE(@PathVariable Integer id){
		
		investorService.deleteById(id);
	}
	
	@RequestMapping(
			value="/investor", 
			method = RequestMethod.GET )
	public @ResponseBody List<Investor> investorList(){
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(principal.toString());
		return investorService.getAll();
	}
	

	
	@RequestMapping(
			value="/investor/from/{from}", 
			method = RequestMethod.GET )
	public @ResponseBody List<Investor> fromGET(@PathVariable Integer from, HttpServletResponse response){
		
		List<Investor> list = investorService.getAll();
		List<Investor> newList = new ArrayList<Investor>();
		boolean error = false;
		int size = list.size();
		
		if (from<1 || from > size){
			error = true;
		}
		
		if (error == true){
			response.setStatus(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
			return null;
		}
		
		for (int i = from; i <= size; i++) {
			newList.add(list.get(i-1));
		}
		
		if (error != true){
			return newList;
		}
		
		return null;
	}
	

	@RequestMapping(
			value="/investor/to/{to}", 
			method = RequestMethod.GET )
	public @ResponseBody List<Investor> toGET(@PathVariable Integer to, HttpServletResponse response){
		
		List<Investor> list = investorService.getAll();
		List<Investor> newList = new ArrayList<Investor>();
		boolean error = false;
		int size = list.size();
		
		if (to<1 || to > size){
			error = true;
		}
		
		if (error == true){
			response.setStatus(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
			return null;
		}
		
		for (int i = 1; i <= to; i++) {
			newList.add(list.get(i-1));
		}
		
		if (error != true){
			return newList;
		}
		
		return null;
		
	}
	

	@RequestMapping(
			value="/investor/from/{from}/to/{to}", 
			method = RequestMethod.GET )
	public @ResponseBody List<Investor> fromtoGET(@PathVariable Integer from, @PathVariable Integer to, HttpServletResponse response){
		List<Investor> list = investorService.getAll();
		List<Investor> newList = new ArrayList<Investor>();
		boolean error = false;
		int size = list.size();
		
		if (from > to){
			int temp = from;
			from = to;
			to = temp;
		}
		if (size < from || size < to){
			error = true;
		}
		if (from == to){
			error=true;
		}
		if (from<1 || to<1){
			error = true;
		}
		
		if (error == true){
			response.setStatus(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
			return null;
		}
		
		for (int i = from; i <= to; i++) {
			newList.add(list.get(i-1));
		}
		
		if (error != true){
			return newList;
		}
		
		return null;
		
	}
	
	@RequestMapping(
			value="/investor/size", 
			method = RequestMethod.GET )
	public @ResponseBody Integer sizeGET(){
		
		return investorService.getAll().size();
	}
	
	@RequestMapping(
			value="/investor/last", 
			method = RequestMethod.GET )
	public @ResponseBody Investor lastGET(){
		
		return investorService.getLastId();
	}
}

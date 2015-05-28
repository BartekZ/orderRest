package pl.proacem.REST;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
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

import pl.proacem.model.MainOrder;
import pl.proacem.service.MainOrderService;

@Secured("ROLE_ADMIN")
@Controller
public class MainOrderREST {

	@Autowired
	@Qualifier("MainOrderService")
	private MainOrderService mainOrderService;
	
	@RequestMapping(
			value="/mainorder/{id}", 
			method = RequestMethod.GET )
	public @ResponseBody MainOrder mainOrderGET(@PathVariable Integer id){
		
		return mainOrderService.getById(id);
	}
	
	@RequestMapping(
			value="/mainorder", 
			method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody MainOrder itemPOST(@RequestBody  MainOrder mainOrder, HttpServletResponse response){
		mainOrderService.add(mainOrder);
		
		response.setHeader("Location", "/mainOrder/" + mainOrder.getId());
		return mainOrder;
	}

	@RequestMapping(
			value="/mainorder", 
			method = RequestMethod.PUT )
	public @ResponseBody MainOrder mainOrderPUT(@RequestBody MainOrder mainOrder){
		
		mainOrderService.update(mainOrder);
		
		return mainOrder;
	}
	
	
	@RequestMapping(
			value="/mainorder/{id}", 
			method = RequestMethod.DELETE )
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void mainOrderDELETE(@PathVariable Integer id){
		
		mainOrderService.deleteById(id);
	}
	
	@RolesAllowed(value = { "ROLE_ADMIN" })
	@RequestMapping(
			value="/mainorder", 
			method = RequestMethod.GET )
	public @ResponseBody List<MainOrder> mainOrderList(){
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
		return mainOrderService.getAll();
	}
	

	
	@RequestMapping(
			value="/mainorder/from/{from}", 
			method = RequestMethod.GET )
	public @ResponseBody List<MainOrder> fromGET(@PathVariable Integer from, HttpServletResponse response){
		
		List<MainOrder> list = mainOrderService.getAll();
		List<MainOrder> newList = new ArrayList<MainOrder>();
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
			value="/mainorder/to/{to}", 
			method = RequestMethod.GET )
	public @ResponseBody List<MainOrder> toGET(@PathVariable Integer to, HttpServletResponse response){
		
		List<MainOrder> list = mainOrderService.getAll();
		List<MainOrder> newList = new ArrayList<MainOrder>();
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
			value="/mainorder/from/{from}/to/{to}", 
			method = RequestMethod.GET )
	public @ResponseBody List<MainOrder> fromtoGET(@PathVariable Integer from, @PathVariable Integer to, HttpServletResponse response){
		List<MainOrder> list = mainOrderService.getAll();
		List<MainOrder> newList = new ArrayList<MainOrder>();
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
			value="/mainorder/size", 
			method = RequestMethod.GET )
	public @ResponseBody Integer sizeGET(){
		
		return mainOrderService.getAll().size();
	}
	
}

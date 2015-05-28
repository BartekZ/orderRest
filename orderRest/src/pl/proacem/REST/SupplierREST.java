package pl.proacem.REST;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.proacem.model.Supplier;
import pl.proacem.service.ServiceInterface;
import pl.proacem.service.SupplierService;

@Secured("ROLE_USER")
@Controller
public class SupplierREST {

	@Autowired
	@Qualifier("SupplierService")
	private SupplierService supplierService;
	
	
	@RequestMapping(
			value="/supplier/{id}", 
			method = RequestMethod.GET )
	public @ResponseBody Supplier itemGET(@PathVariable Integer id){
		
		return supplierService.getById(id);
	}
	
	
	@RequestMapping(
			value="/supplier", 
			method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Supplier itemPOST(@RequestBody  Supplier supplier, HttpServletResponse response){
		supplierService.add(supplier);
		
		
		response.setHeader("Location", "/supplier/" + supplier.getId());
		
		return supplier;

	}

	
	@RequestMapping(
			value="/supplier", 
			method = RequestMethod.PUT )
	public @ResponseBody Supplier SupplierPUT(@RequestBody Supplier supplier){
		
		supplierService.update(supplier);
		
		return supplier;
	}
	
	
	@RequestMapping(
			value="/supplier/{id}", 
			method = RequestMethod.DELETE )
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void SupplierDELETE(@PathVariable Integer id){
		
		supplierService.deleteById(id);
	}
	
	@RequestMapping(
			value="/supplier", 
			method = RequestMethod.GET )
	public @ResponseBody List<Supplier> supplierList(){
		
		return supplierService.getAll();
	}
	
	@RequestMapping(
			value="/supplier/from/{from}", 
			method = RequestMethod.GET )
	public @ResponseBody List<Supplier> fromGET(@PathVariable Integer from, HttpServletResponse response){
		
		List<Supplier> list = supplierService.getAll();
		List<Supplier> newList = new ArrayList<Supplier>();
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
			value="/supplier/to/{to}", 
			method = RequestMethod.GET )
	public @ResponseBody List<Supplier> toGET(@PathVariable Integer to, HttpServletResponse response){
		
		List<Supplier> list = supplierService.getAll();
		List<Supplier> newList = new ArrayList<Supplier>();
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
			value="/supplier/from/{from}/to/{to}", 
			method = RequestMethod.GET )
	public @ResponseBody List<Supplier> fromtoGET(@PathVariable Integer from, @PathVariable Integer to, HttpServletResponse response){
		List<Supplier> list = supplierService.getAll();
		List<Supplier> newList = new ArrayList<Supplier>();
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
			value="/supplier/size", 
			method = RequestMethod.GET )
	public @ResponseBody Integer sizeGET(){
		
		return supplierService.getAll().size();
	}
	
	@RequestMapping(
			value="/supplier/last", 
			method = RequestMethod.GET )
	public @ResponseBody Supplier lastGET(){
		
		return supplierService.getLastId();
	}
}

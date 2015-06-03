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

import pl.proacem.dao.HbnSingleOrderDao;
import pl.proacem.model.SingleOrder;
import pl.proacem.service.SingleOrderService;

@Secured("ROLE_USER")
@Controller
public class SingleOrderREST {

	@Autowired
	@Qualifier("SingleOrderService")
	private SingleOrderService singleorderService;
	
	
	@RequestMapping(
			value="/singleorder/mainorder/{id}", 
			method = RequestMethod.GET )
	public @ResponseBody List<SingleOrder> singleOrderList2(@PathVariable int id){
		/*
		SingleOrder sss = new SingleOrder();
		sss.setId(666);
		List<SingleOrder> aaa = new ArrayList<SingleOrder>();
		aaa.add(sss);
		Integer id2 = mo;
		System.out.println(id2);
		System.out.println();
		*/
		return singleorderService.getByMainOrder(id);
		
		//return singleorderService.getByMainOrder(1);
	}
	
	@RequestMapping(
			value="/singleorder/{id}", 
			method = RequestMethod.GET )
	public @ResponseBody SingleOrder itemGET(@PathVariable Integer id){
		
		return singleorderService.getById(id);
	}
	
	
	@RequestMapping(
			value="/singleorder", 
			method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody SingleOrder itemPOST(@RequestBody  SingleOrder singleorder, HttpServletResponse response){
		singleorderService.add(singleorder);
		
		
		response.setHeader("Location", "/singleorder/" + singleorder.getId());
		
		return singleorder;

	}

	
	@RequestMapping(
			value="/singleorder", 
			method = RequestMethod.PUT )
	public @ResponseBody SingleOrder SingleOrderPUT(@RequestBody SingleOrder singleorder){
		
		singleorderService.update(singleorder);
		
		return singleorder;
	}
	

	
	@RequestMapping(
			value="/singleorder/{id}", 
			method = RequestMethod.DELETE )
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void SingleOrderDELETE(@PathVariable Integer id){
		
		singleorderService.deleteById(id);
	}
	
	@RequestMapping(
			value="/singleorder", 
			method = RequestMethod.GET )
	public @ResponseBody List<SingleOrder> singleOrderList(){
		
		return singleorderService.getAll();
	}
	

	
	@RequestMapping(
			value="/singleorder/from/{from}", 
			method = RequestMethod.GET )
	public @ResponseBody List<SingleOrder> fromGET(@PathVariable Integer from, HttpServletResponse response){
		
		List<SingleOrder> list = singleorderService.getAll();
		List<SingleOrder> newList = new ArrayList<SingleOrder>();
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
			value="/singleorder/to/{to}", 
			method = RequestMethod.GET )
	public @ResponseBody List<SingleOrder> toGET(@PathVariable Integer to, HttpServletResponse response){
		
		List<SingleOrder> list = singleorderService.getAll();
		List<SingleOrder> newList = new ArrayList<SingleOrder>();
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
			value="/singleorder/from/{from}/to/{to}", 
			method = RequestMethod.GET )
	public @ResponseBody List<SingleOrder> fromtoGET(@PathVariable Integer from, @PathVariable Integer to, HttpServletResponse response){
		List<SingleOrder> list = singleorderService.getAll();
		List<SingleOrder> newList = new ArrayList<SingleOrder>();
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
			value="/singleorder/size", 
			method = RequestMethod.GET )
	public @ResponseBody Integer sizeGET(){
		List<SingleOrder> list = new ArrayList<SingleOrder>();
		HbnSingleOrderDao hbnSingleOrderDao = new HbnSingleOrderDao();
		list = hbnSingleOrderDao.findByOrderNumber("452");
		System.out.println(list);
		return singleorderService.getAll().size();
	}

	
}

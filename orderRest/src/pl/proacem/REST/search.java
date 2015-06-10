package pl.proacem.REST;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.proacem.model.Investor;
import pl.proacem.model.MainOrder;
import pl.proacem.model.SingleOrder;
import pl.proacem.model.Supplier;
import pl.proacem.service.InvestorService;
import pl.proacem.service.MainOrderService;
import pl.proacem.service.SingleOrderService;
import pl.proacem.service.SupplierService;

@Controller
@RequestMapping(
		value="/search",
		method = RequestMethod.GET )
public class search {

	@Autowired
	@Qualifier("SingleOrderService")
	private SingleOrderService singleorderService;
	
	@Autowired
	@Qualifier("InvestorService")
	private InvestorService investorService;
	
	@Autowired
	@Qualifier("MainOrderService")
	private MainOrderService mainOrderService;
	
	@Autowired
	@Qualifier("SupplierService")
	private SupplierService supplierService;
	
	@RequestMapping(
			value="/singleorder/{word}")
	public @ResponseBody List<SingleOrder> searchSingleOrders(@PathVariable String word){
		
		HashSet<SingleOrder> list = new HashSet<SingleOrder>();
		
		list.addAll(singleorderService.findByOrderNumber(word));
		list.addAll(singleorderService.findByOfferNumber(word));
		list.addAll(singleorderService.findByOther(word));
		list.addAll(singleorderService.findBySpecification(word));
		

		
		List<SingleOrder> list2 = new ArrayList<SingleOrder>(list);
		
		return list2;
		
	}
	
	@RequestMapping(
			value="/investor/{word}")
	public @ResponseBody List<Investor> searchInvestors(@PathVariable String word){
		
		HashSet<Investor> list = new HashSet<Investor>();
		if (investorService.findByName(word) != null){
			list.addAll(investorService.findByName(word));
		}
		if (investorService.findByDescription(word) != null) {
			list.addAll(investorService.findByDescription(word));
		}
		if (investorService.findByNote(word) != null) {
			list.addAll(investorService.findByNote(word));
		}
		
		
		
		List<Investor> list2 = new ArrayList<Investor>(list);
		
		return list2;
		
	}
	
	@RequestMapping(
			value="/mainorder/{word}")
	public @ResponseBody List<MainOrder> searchMainOrders(@PathVariable String word){
		
		HashSet<MainOrder> list = new HashSet<MainOrder>();
		if (mainOrderService.findByMainNumber(word) != null){
			
			list.addAll(mainOrderService.findByMainNumber(word));
		}
		if (mainOrderService.findByTopic(word) != null){

			list.addAll(mainOrderService.findByTopic(word));
		}
		
		List<MainOrder> list2 = new ArrayList<MainOrder>(list);
		
		return list2;
		
	}
	
	@RequestMapping(
			value="/supplier/{word}")
	public @ResponseBody List<Supplier> searchSuppliers(@PathVariable String word){
		
		HashSet<Supplier> list = new HashSet<Supplier>();
		if (supplierService.findByName(word) != null) {
			list.addAll(supplierService.findByName(word));
		}
		if (supplierService.findByDescription(word) != null) {
			list.addAll(supplierService.findByDescription(word));
		}
		if (supplierService.findByAddress(word) != null) {
			list.addAll(supplierService.findByAddress(word));
		}
		if (supplierService.findByPhone(word) != null) {
			list.addAll(supplierService.findByPhone(word));
		}
		
		
		
		
		List<Supplier> list2 = new ArrayList<Supplier>(list);
		
		return list2;
	}
}

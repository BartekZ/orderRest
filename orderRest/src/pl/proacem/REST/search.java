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

import pl.proacem.model.SingleOrder;
import pl.proacem.service.SingleOrderService;

@Controller
@RequestMapping(
		value="/search",
		method = RequestMethod.GET )
public class search {

	@Autowired
	@Qualifier("SingleOrderService")
	private SingleOrderService singleorderService;
	
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
}

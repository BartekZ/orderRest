
package pl.proacem.RESTwrong;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;

import pl.proacem.model.ModelInterface;
import pl.proacem.service.ServiceInterface;


public class AbstractREST<T extends ModelInterface> implements REST<T> {

	
	public void setService(ServiceInterface<T> service) {
		this.service = service;
	}

	protected ServiceInterface<T> service;

	private String itemTable;
	
	@Override
	public T itemGET(@PathVariable Integer id) {
		return service.getById(id);
	}

	@Override
	public T itemPOST(T item, HttpServletResponse response) {
		service.add(item);
		
		response.setHeader("Location", "/" + itemTable +"/" + item.getId());
		
		return item;
	}

	@Override
	public T itemPUT(T item) {
		service.update(item);
		
		return item;
	}

	@Override
	public void itemDELETE(Integer id) {
		service.deleteById(id);
	}

	@Override
	public List<T> itemsGET() {
		
		return service.getAll();
	}

	@Override
	public List<T> fromGET(Integer from, HttpServletResponse response) {
		List<T> list = service.getAll();
		List<T> newList = new ArrayList<T>();
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

	@Override
	public List<T> toGET(Integer to, HttpServletResponse response) {
		List<T> list = service.getAll();
		List<T> newList = new ArrayList<T>();
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

	@Override
	public List<T> fromtoGET(Integer from, Integer to,
			HttpServletResponse response) {
		List<T> list = service.getAll();
		List<T> newList = new ArrayList<T>();
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

	@Override
	public Integer sizeGET() {
		return service.getAll().size();
	}



	
}

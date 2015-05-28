package pl.proacem.RESTwrong;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.proacem.model.Person;
import pl.proacem.service.ServiceInterface;

public class PersonREST implements REST<Person>{
	
	@Autowired
	@Qualifier("PersonService")
	protected ServiceInterface<Person> service;
	
	
	@Autowired
	AbstractREST<Person> rest;
	


	String itemTable = "Person";
	
	@RequestMapping(
			value="/person/{id}", 
			method = RequestMethod.GET )
	public @ResponseBody Person itemGET(@PathVariable Integer id) {
		
		return rest.itemGET(id);
	}

	@RequestMapping(
			value="/person", 
			method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Person itemPOST(@RequestBody Person item, HttpServletResponse response) {
		
		return rest.itemPOST(item, response);
	}

	@RequestMapping(
			value="/person", 
			method = RequestMethod.PUT )
	public @ResponseBody Person itemPUT(@RequestBody Person item) {
		
		return rest.itemPUT(item);
	}

	@RequestMapping(
			value="/person/{id}", 
			method = RequestMethod.DELETE )
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody void itemDELETE(@PathVariable Integer id) {
		
		rest.itemDELETE(id);
	}

	@RequestMapping(
			value="/person/all", 
			method = RequestMethod.GET )
	public @ResponseBody List<Person> itemsGET() {
		
		return rest.itemsGET();
	}

	@RequestMapping(
			value="/person/from/{from}", 
			method = RequestMethod.GET )
	public @ResponseBody List<Person> fromGET(@PathVariable Integer from, HttpServletResponse response) {
		
		return rest.fromGET(from, response);
	}

	@RequestMapping(
			value="/person/to/{to}", 
			method = RequestMethod.GET )
	public @ResponseBody List<Person> toGET(@PathVariable Integer to, HttpServletResponse response) {
		
		return rest.toGET(to, response);
	}

	@RequestMapping(
			value="/person/from/{from}/to/{to}", 
			method = RequestMethod.GET )
	public @ResponseBody List<Person> fromtoGET(@PathVariable Integer from, @PathVariable Integer to,
			HttpServletResponse response) {
		
		return rest.fromtoGET(from, to, response);
	}

	@RequestMapping(
			value="/person/size", 
			method = RequestMethod.GET )
	public @ResponseBody Integer sizeGET() {
		
		return rest.sizeGET();
	}
	
}

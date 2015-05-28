package pl.proacem.REST;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.proacem.model.Person;
import pl.proacem.service.PersonService;

@Controller
public class PersonREST implements REST<Person> {

	@Autowired
	private PersonService personService;

	@Override
	@RequestMapping(
			value="/person/{id}",
			method = RequestMethod.GET )
	public @ResponseBody Person itemGET(@PathVariable Integer id){
		Person person =personService.getById(id);
		person.setPass(null);
		return person;
	}

	@Secured("ROLE_ADMIN")
	@Override
	@RequestMapping(
			value="/person", 
			method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Person itemPOST(@RequestBody  Person person, HttpServletResponse response){

		personService.add(person);

		response.setHeader("Location", "/person/" + person.getId());
		
		return person;

	}

	@Override
	@RequestMapping(
			value="/person", 
			method = RequestMethod.PUT )
	public @ResponseBody Person itemPUT(@RequestBody Person person){
		personService.update(person);
		
		return person;
	}
	
	

	@Override
	@RequestMapping(
			value="/person/{id}", 
			method = RequestMethod.DELETE )
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void itemDELETE(@PathVariable Integer id){
		
		personService.deleteById(id);
	}
	

	@Override
	@RequestMapping(
			value="/person", 
			method = RequestMethod.GET )
	public @ResponseBody List<Person> itemsGET(HttpServletResponse response){

		response.setHeader("Access-Control-Allow-Origin", "*");
		return personService.getAll();
	}
	

	@Override
	@RequestMapping(
			value="/person/from/{from}", 
			method = RequestMethod.GET )
	public @ResponseBody List<Person> fromGET(@PathVariable Integer from, HttpServletResponse response){
		
		List<Person> list = personService.getAll();
		List<Person> newList = new ArrayList<Person>();
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
	@RequestMapping(
			value="/person/to/{to}", 
			method = RequestMethod.GET )
	public @ResponseBody List<Person> toGET(@PathVariable Integer to, HttpServletResponse response){
		
		List<Person> list = personService.getAll();
		List<Person> newList = new ArrayList<Person>();
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
	@RequestMapping(
			value="/person/from/{from}/to/{to}", 
			method = RequestMethod.GET )
	public @ResponseBody List<Person> fromtoGET(@PathVariable Integer from, @PathVariable Integer to, HttpServletResponse response){
		List<Person> list = personService.getAll();
		List<Person> newList = new ArrayList<Person>();
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
	@RequestMapping(
			value="/person/size", 
			method = RequestMethod.GET )
	public @ResponseBody Integer sizeGET(){
		
		return personService.getAll().size();
	}
	
	
	@RequestMapping(
			value="/person/last", 
			method = RequestMethod.GET )
	public @ResponseBody Person lastGET(){
		
		return personService.getLastId();
	}
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(
			value="/person/login", 
			method = RequestMethod.POST, 
			consumes="application/json", 
			produces="application/json" )
	
	public @ResponseBody Person getLogin(@RequestBody Person person){

		Person person_readed = personService.getLogin(person);
		if (person_readed != null){
			List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
			String[] roles = person_readed.getAuthority().split(",");
			for(String item : roles){
				authList.add(new GrantedAuthorityImpl(item));
			}
			
			Authentication request = new UsernamePasswordAuthenticationToken(person.getLogin(), person.getPass(), authList);
			SecurityContextHolder.getContext().setAuthentication(request);
		}
		
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		
		return person_readed;
		
	}
	
	@RequestMapping(
			value="/person/logout", 
			method = RequestMethod.GET, 
			produces="application/json" )
	public @ResponseBody Integer Logout(){
		
		SecurityContextHolder.getContext().setAuthentication(null);
		return 0;
		
	}
	
	
	
	
}

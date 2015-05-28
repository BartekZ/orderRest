package pl.proacem.controlers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.proacem.dao.PersonDao;
import pl.proacem.model.Investor;
import pl.proacem.model.Item;
import pl.proacem.model.MainOrder;
import pl.proacem.model.Person;

@Controller
public class hello{
	
	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private PersonDao personDao;

	@RequestMapping(value="/hello2")
	public String sayhello(Model model){

		Investor investor = (Investor) em.createQuery("from Investor").getResultList().get(0);
		
		
		MainOrder mainorder = (MainOrder) em.createQuery("from MainOrder").getSingleResult();
		
		model.addAttribute("investor", investor);
		model.addAttribute("mainorder", mainorder);
		
		return "hello2";
	}
	
	@RequestMapping(value="/add")
	@Transactional
	public String addPerson(Model model){
		
		Person newperson = new Person();
		newperson.setName("osoba2");
		newperson.setLogin("login2");
		newperson.setPass("pass2");
		newperson.setEmail("email@iie.com");
		newperson.setPhone("44334553");
		newperson.setDescription("jakis opis 2222");
		newperson.setNotes("jakies notatki2");
		newperson.setAuthority("user");
		newperson.setStatus(1);
		
		em.persist(newperson);
		
		return "redirect:hello2";
	}
	


	
	
	@RequestMapping(
			value="/item", 
			method = RequestMethod.GET)
	public @ResponseBody Item itemGET(){
		
		System.out.println("pobrano item");
		
		Item item = new Item();
		
		item.setId(1);
		item.setName("imie1");
		item.setLogin("login1");
		
		return item;
	}
	
	@RequestMapping(
			value="/item", 
			method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody void itemPOST(@RequestBody Item item){
		
		System.out.println("wyslano item");
		System.out.println(item);

		
	}

	
	
}

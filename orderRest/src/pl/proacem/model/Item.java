package pl.proacem.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6273986465440624143L;
	private int id;
	private String name;
	private String login;
	
	
	public Item() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", login=" + login + "]";
	}
	
	
}

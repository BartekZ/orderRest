package pl.proacem.model;

import java.util.Date;

public abstract interface ModelInterface {

	public int getId();
	public void setCreated(Date date);
	public void setUpdated(Date date);
	
}

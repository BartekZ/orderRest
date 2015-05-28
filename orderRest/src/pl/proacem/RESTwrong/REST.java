package pl.proacem.RESTwrong;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public interface REST<T> {

	public abstract T itemGET(Integer id);

	public abstract T itemPOST(T item, HttpServletResponse response);

	public abstract T itemPUT(T item);

	public abstract void itemDELETE(Integer id);

	public abstract List<T> itemsGET();

	public abstract List<T> fromGET(Integer from, HttpServletResponse response);

	public abstract List<T> toGET(Integer to, HttpServletResponse response);

	public abstract List<T> fromtoGET(Integer from, Integer to,
			HttpServletResponse response);

	public abstract Integer sizeGET();

}
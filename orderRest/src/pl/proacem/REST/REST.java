package pl.proacem.REST;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public interface REST<T> {

	T itemGET(Integer id);

	T itemPOST(T item, HttpServletResponse response);

	T itemPUT(T item);

	void itemDELETE(Integer id);

	List<T> itemsGET(HttpServletResponse response);

	List<T> fromGET(Integer from, HttpServletResponse response);

	List<T> toGET(Integer to, HttpServletResponse response);

	List<T> fromtoGET(Integer from, Integer to, HttpServletResponse response);

	Integer sizeGET();

}
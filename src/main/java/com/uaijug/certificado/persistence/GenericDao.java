package com.uaijug.certificado.persistence;

import java.util.List;

public interface GenericDao<T, ID> {

	T create(T t);

	T update(T t);

	void delete(T t);

	T find(ID id);

	List<T> findAll();

}

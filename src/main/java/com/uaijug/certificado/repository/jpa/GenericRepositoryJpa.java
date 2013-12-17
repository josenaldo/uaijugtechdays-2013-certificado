package com.uaijug.certificado.repository.jpa;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.uaijug.certificado.repository.GenericRepository;

public abstract class GenericRepositoryJpa<T> implements GenericRepository<T> {

	private EntityManagerFactory	entityManagerFactory;

	private Class<T>				type;

	public EntityManager getEntityManager() {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em;
	}
	
	@SuppressWarnings("unchecked")
	public GenericRepositoryJpa(String persistenceUnit) {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<T>) pt.getActualTypeArguments()[0];

		entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
	}

	public T create(final T t) {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(t);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			em.close();
		}

		return t;
	}

	public T update(final T t) {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.merge(t);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			em.close();
		}

		return t;
	}
	
	public void delete(final Object id) {

		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.remove(em.getReference(type, id));
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			em.close();
		}

	}

	public T find(final Object id) {
		EntityManager em = getEntityManager();

		return em.find(type, id);
	}

	public List<T> findAll() {
		EntityManager em = getEntityManager();
		try {
			Query query = em.createQuery("from " + type.getName() + " t");

			@SuppressWarnings("unchecked")
			List<T> items = query.getResultList();

			return items;

		} finally {
			em.close();
		}
	}
}

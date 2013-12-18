package com.uaijug.certificado.repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public abstract class AbstractRepository<T, ID> {

	@Inject
	private EntityManagerFactory entityManagerFactory;

	private Class<T> type;

	public EntityManager getEntityManager() {
		EntityManager em = this.entityManagerFactory.createEntityManager();
		return em;
	}

	@SuppressWarnings("unchecked")
	public AbstractRepository() {
		Type t = this.getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		this.type = (Class<T>) pt.getActualTypeArguments()[0];
	}

	public T create(final T t) {
		EntityManager em = this.getEntityManager();
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
		EntityManager em = this.getEntityManager();
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

	public void delete(final ID id) {

		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.remove(em.getReference(this.type, id));
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			em.close();
		}

	}

	public T find(final ID id) {
		EntityManager em = this.getEntityManager();

		return em.find(this.type, id);
	}

	public List<T> findAll() {
		EntityManager em = this.getEntityManager();
		try {
			Query query = em.createQuery("from " + this.type.getName() + " t");

			@SuppressWarnings("unchecked")
			List<T> items = query.getResultList();

			return items;

		} finally {
			em.close();
		}
	}
}

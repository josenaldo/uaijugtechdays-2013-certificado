package com.uaijug.certificado.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.uaijug.certificado.model.Configuration;

public class ConfigurationRepository extends
		AbstractRepository<Configuration, Long> {

	public Configuration findByKey(String key) {
		EntityManager em = this.getEntityManager();
		try {

			Query query = em
					.createQuery("SELECT c FROM Configuration c WHERE c.key=:key");
			query.setParameter("key", key);

			Configuration configuration = (Configuration) query
					.getSingleResult();

			return configuration;

		} finally {
			em.close();
		}
	}
}

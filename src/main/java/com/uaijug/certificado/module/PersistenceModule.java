package com.uaijug.certificado.module;

import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.uaijug.certificado.persistence.ParticipantDao;
import com.uaijug.certificado.persistence.jpa.ParticipantDaoJpa;

public class PersistenceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ParticipantDao.class).to(ParticipantDaoJpa.class);
	}

	@Provides
	public EntityManagerFactory createEntityManagerFactory(
			@Named("persistenceUnit") String persistenceUnitName) {
		return Persistence.createEntityManagerFactory(persistenceUnitName);
	}

}

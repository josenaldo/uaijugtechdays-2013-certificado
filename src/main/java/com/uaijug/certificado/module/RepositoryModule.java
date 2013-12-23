package com.uaijug.certificado.module;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.uaijug.certificado.config.ConfigPersistenceUnit;

public class RepositoryModule extends AbstractModule {

	@Override
	protected void configure() {

	}

	@Provides
	public EntityManagerFactory createEntityManagerFactory(
			@ConfigPersistenceUnit String persistenceUnitName) {
		return Persistence.createEntityManagerFactory(persistenceUnitName);
	}

}

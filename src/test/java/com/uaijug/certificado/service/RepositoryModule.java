package com.uaijug.certificado.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.uaijug.certificado.config.ConfigPersistenceUnit;
import com.uaijug.certificado.repository.ParticipantRepository;

public class RepositoryModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ParticipantRepository.class);
	}

	@Provides
	public EntityManagerFactory createEntityManagerFactory(
			@ConfigPersistenceUnit String persistenceUnitName) {
		return Persistence.createEntityManagerFactory(persistenceUnitName);
	}

}

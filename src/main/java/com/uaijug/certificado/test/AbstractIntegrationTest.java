package com.uaijug.certificado.test;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.jukito.JukitoRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(JukitoRunner.class)
public abstract class AbstractIntegrationTest {

	@Inject
	private DatabaseLoader loader;

	@Inject
	private EntityManagerFactory entityManagerFactory;

	@Before
	public void configureDatabase() throws Exception {
		this.entityManagerFactory.createEntityManager();
		this.loader.setup();
	}

	@After
	public void cleanDatabase() throws Exception {
		this.loader.tearDown();
	}

}

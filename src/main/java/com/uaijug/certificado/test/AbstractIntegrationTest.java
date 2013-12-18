package com.uaijug.certificado.test;

import javax.inject.Inject;

import org.jukito.JukitoRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(JukitoRunner.class)
public abstract class AbstractIntegrationTest {

	@Inject
	private DatabaseLoader loader;

	@Before
	public void configureDatabase() throws Exception {
		loader.setup();
	}

	@After
	public void cleanDatabase() throws Exception {
		loader.tearDown();
	}

}

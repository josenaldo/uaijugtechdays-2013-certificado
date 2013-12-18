package com.uaijug.certificado.module;

import com.google.inject.AbstractModule;
import com.uaijug.certificado.config.ConfigDatabaseDriver;
import com.uaijug.certificado.config.ConfigDatabasePassword;
import com.uaijug.certificado.config.ConfigDatabaseUrl;
import com.uaijug.certificado.config.ConfigDatabaseUser;
import com.uaijug.certificado.config.ConfigPersistenceUnit;
import com.uaijug.certificado.test.config.ConfigTestDataset;

public class BasicConfigurationTestModule extends AbstractModule {

	@Override
	protected void configure() {
		this.bind(String.class).annotatedWith(ConfigPersistenceUnit.class)
				.toInstance("CertificadosPUTest");

		this.bind(String.class).annotatedWith(ConfigDatabaseDriver.class)
				.toInstance("org.hsqldb.jdbcDriver");

		this.bind(String.class).annotatedWith(ConfigDatabaseUrl.class)
				.toInstance("jdbc:hsqldb:mem:certificados");

		this.bind(String.class).annotatedWith(ConfigDatabaseUser.class)
				.toInstance("sa");

		this.bind(String.class).annotatedWith(ConfigDatabasePassword.class)
				.toInstance("");

		this.bind(String.class)
				.annotatedWith(ConfigTestDataset.class)
				.toInstance(
						"src/integration-test/resources/database/database-default.xml");
	}

}

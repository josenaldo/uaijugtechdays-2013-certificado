package com.uaijug.certificado.module;

import com.google.inject.AbstractModule;
import com.uaijug.certificado.config.ConfigDatabaseDriver;
import com.uaijug.certificado.config.ConfigDatabasePassword;
import com.uaijug.certificado.config.ConfigDatabaseUrl;
import com.uaijug.certificado.config.ConfigDatabaseUser;
import com.uaijug.certificado.config.ConfigParticipantReportTemplate;
import com.uaijug.certificado.config.ConfigPersistenceUnit;

public class BasicConfigurationModule extends AbstractModule {

	@Override
	protected void configure() {
		this.bind(String.class).annotatedWith(ConfigPersistenceUnit.class)
				.toInstance("CertificadosPU");

		this.bind(String.class).annotatedWith(ConfigDatabaseDriver.class)
				.toInstance("org.hsqldb.jdbcDriver");

		this.bind(String.class)
				.annotatedWith(ConfigDatabaseUrl.class)
				.toInstance(
						"jdbc:hsqldb:file:./db/prod/certificados.db;shutdown=true");

		this.bind(String.class).annotatedWith(ConfigDatabaseUser.class)
				.toInstance("sa");

		this.bind(String.class).annotatedWith(ConfigDatabasePassword.class)
				.toInstance("");

		this.bind(String.class)
				.annotatedWith(ConfigParticipantReportTemplate.class)
				.toInstance("Participant.jrxml");
	}

}

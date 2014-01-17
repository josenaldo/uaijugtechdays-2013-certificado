package com.uaijug.certificado.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.uaijug.certificado.config.ConfigDatabaseDriver;
import com.uaijug.certificado.config.ConfigDatabasePassword;
import com.uaijug.certificado.config.ConfigDatabaseUrl;
import com.uaijug.certificado.config.ConfigDatabaseUser;
import com.uaijug.certificado.config.ConfigPersistenceUnit;
import com.uaijug.certificado.config.ConfigReportBackgroundPage1;
import com.uaijug.certificado.config.ConfigReportBackgroundPage2;
import com.uaijug.certificado.config.ConfigReportParticipantTemplate;
import com.uaijug.certificado.config.ConfigReportTemplateDir;

public class BasicConfigurationModule extends AbstractModule {

	@Override
	protected void configure() {

		this.bind(String.class).annotatedWith(Names.named("configFile"))
				.toInstance("config.properties");

		this.configureDatabase();

		this.configureReport();

	}

	private void configureDatabase() {
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
	}

	private void configureReport() {
		this.bind(String.class).annotatedWith(ConfigReportTemplateDir.class)
				.toInstance("com/uaijug/certificado/report/template/");

		this.bind(String.class)
				.annotatedWith(ConfigReportParticipantTemplate.class)
				.toInstance("Participant.jrxml");

		this.bind(String.class)
				.annotatedWith(ConfigReportBackgroundPage1.class)
				.toInstance("page1.jpg");

		this.bind(String.class)
				.annotatedWith(ConfigReportBackgroundPage2.class)
				.toInstance("page2.jpg");
	}

}

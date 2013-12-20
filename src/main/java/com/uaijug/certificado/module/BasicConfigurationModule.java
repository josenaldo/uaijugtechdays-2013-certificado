package com.uaijug.certificado.module;

import com.google.inject.AbstractModule;
import com.uaijug.certificado.config.ConfigDatabaseDriver;
import com.uaijug.certificado.config.ConfigDatabasePassword;
import com.uaijug.certificado.config.ConfigDatabaseUrl;
import com.uaijug.certificado.config.ConfigDatabaseUser;
import com.uaijug.certificado.config.ConfigEmailCharset;
import com.uaijug.certificado.config.ConfigEmailFrom;
import com.uaijug.certificado.config.ConfigEmailPassword;
import com.uaijug.certificado.config.ConfigEmailSmtpAuth;
import com.uaijug.certificado.config.ConfigEmailSmtpHost;
import com.uaijug.certificado.config.ConfigEmailSmtpPort;
import com.uaijug.certificado.config.ConfigEmailStartTlsEnabled;
import com.uaijug.certificado.config.ConfigEmailTextType;
import com.uaijug.certificado.config.ConfigPersistenceUnit;
import com.uaijug.certificado.config.ConfigReportBackgroundPage1;
import com.uaijug.certificado.config.ConfigReportBackgroundPage2;
import com.uaijug.certificado.config.ConfigReportParticipantTemplate;
import com.uaijug.certificado.config.ConfigReportTemplateDir;

public class BasicConfigurationModule extends AbstractModule {

	@Override
	protected void configure() {
		this.configureDatabase();

		this.configureReport();

		this.bind(String.class).annotatedWith(ConfigEmailFrom.class)
				.toInstance("");

		this.bind(String.class).annotatedWith(ConfigEmailPassword.class)
				.toInstance("");

		this.bind(String.class).annotatedWith(ConfigEmailSmtpAuth.class)
				.toInstance("");

		this.bind(String.class).annotatedWith(ConfigEmailStartTlsEnabled.class)
				.toInstance("");

		this.bind(String.class).annotatedWith(ConfigEmailSmtpHost.class)
				.toInstance("");

		this.bind(String.class).annotatedWith(ConfigEmailSmtpPort.class)
				.toInstance("");

		this.bind(String.class).annotatedWith(ConfigEmailCharset.class)
				.toInstance("");

		this.bind(String.class).annotatedWith(ConfigEmailTextType.class)
				.toInstance("");

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

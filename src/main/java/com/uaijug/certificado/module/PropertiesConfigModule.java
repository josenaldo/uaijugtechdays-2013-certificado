package com.uaijug.certificado.module;

import java.io.IOException;
import java.util.Properties;

import javax.inject.Named;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import com.uaijug.certificado.config.ConfigDatabaseDriver;
import com.uaijug.certificado.config.ConfigDatabasePassword;
import com.uaijug.certificado.config.ConfigDatabaseUrl;
import com.uaijug.certificado.config.ConfigDatabaseUser;
import com.uaijug.certificado.config.ConfigEmailCharset;
import com.uaijug.certificado.config.ConfigEmailMessageParticipant;
import com.uaijug.certificado.config.ConfigEmailPassword;
import com.uaijug.certificado.config.ConfigEmailSmtpAuth;
import com.uaijug.certificado.config.ConfigEmailSmtpHost;
import com.uaijug.certificado.config.ConfigEmailSmtpPort;
import com.uaijug.certificado.config.ConfigEmailSmtpTransportType;
import com.uaijug.certificado.config.ConfigEmailStartTlsEnabled;
import com.uaijug.certificado.config.ConfigEmailTextType;
import com.uaijug.certificado.config.ConfigEmailUsername;
import com.uaijug.certificado.config.ConfigPersistenceUnit;
import com.uaijug.certificado.config.ConfigReportBackgroundPage1;
import com.uaijug.certificado.config.ConfigReportBackgroundPage2;
import com.uaijug.certificado.config.ConfigReportGeneratedDir;
import com.uaijug.certificado.config.ConfigReportParticipantTemplate;
import com.uaijug.certificado.config.ConfigReportTemplateDir;
import com.uaijug.certificado.module.loader.FilePropertiesLoader;
import com.uaijug.certificado.module.loader.PropertiesLoader;

public class PropertiesConfigModule extends AbstractModule {

	@Override
	protected void configure() {
		try {
			String configFile = getConfigFile();
			Properties properties = getProperties(configFile);
			Names.bindProperties(binder(), properties);
		} catch (IOException e) {
			throw new IllegalArgumentException(
					"Problema ao carregar o arquivo de configuração", e);
		}
	}

	protected String getConfigFile() {
		return "d:\\config.properties";
	}

	protected PropertiesLoader getPropertiesLoader() {
		return new FilePropertiesLoader();
	}

	protected Properties getProperties(String path) throws IOException {
		Properties properties = getPropertiesLoader().loadProperties(path);

		return properties;
	}

	@Provides
	@ConfigDatabaseDriver
	public String getConfigDatabaseDriver(
			@Named("database.driver") String property) {
		return property;
	}

	@Provides
	@ConfigDatabasePassword
	public String getConfigDatabasePassword(
			@Named("database.password") String property) {
		return property;
	}

	@Provides
	@ConfigDatabaseUrl
	public String getConfigDatabaseUrl(@Named("database.url") String property) {
		return property;
	}

	@Provides
	@ConfigDatabaseUser
	public String getConfigDatabaseUser(@Named("database.user") String property) {
		return property;
	}

	@Provides
	@ConfigEmailCharset
	public String getConfigEmailCharset(@Named("email.charset") String property) {
		return property;
	}

	@Provides
	@ConfigEmailMessageParticipant
	public String getConfigEmailMessageParticipant(
			@Named("email.message.participant") String property) {
		return property;
	}

	@Provides
	@ConfigEmailPassword
	public String getConfigEmailPassword(
			@Named("email.password") String property) {
		return property;
	}

	@Provides
	@ConfigEmailSmtpAuth
	public String getConfigEmailSmtpAuth(
			@Named("email.smtp.auth") String property) {
		return property;
	}

	@Provides
	@ConfigEmailSmtpHost
	public String getConfigEmailSmtpHost(
			@Named("email.smtp.host") String property) {
		return property;
	}

	@Provides
	@ConfigEmailSmtpPort
	public int getConfigEmailSmtpPort(@Named("email.smtp.port") String property) {

		return Integer.valueOf(property);
	}

	@Provides
	@ConfigEmailSmtpTransportType
	public String getConfigEmailSmtpTransportType(
			@Named("email.smtp.transport.type") String property) {
		return property;
	}

	@Provides
	@ConfigEmailStartTlsEnabled
	public String getConfigEmailStartTlsEnabled(
			@Named("email.smtp.starttls.enable") String property) {
		return property;
	}

	@Provides
	@ConfigEmailTextType
	public String getConfigEmailTextType(
			@Named("email.text.type") String property) {
		return property;
	}

	@Provides
	@ConfigEmailUsername
	public String getConfigEmailUsername(
			@Named("email.username") String property) {
		return property;
	}

	@Provides
	@ConfigPersistenceUnit
	public String getConfigPersistenceUnit(
			@Named("database.persistence.unit") String property) {
		return property;
	}

	@Provides
	@ConfigReportBackgroundPage1
	public String getConfigReportBackgroundPage1(
			@Named("report.template.participant.page1") String property) {
		return property;
	}

	@Provides
	@ConfigReportBackgroundPage2
	public String getConfigReportBackgroundPage2(
			@Named("report.template.participant.page2") String property) {
		return property;
	}

	@Provides
	@ConfigReportGeneratedDir
	public String getConfigReportGeneratedDir(
			@Named("report.generated.dir") String property) {
		return property;
	}

	@Provides
	@ConfigReportParticipantTemplate
	public String getConfigReportParticipantTemplate(
			@Named("report.template.participant.file") String property) {
		return property;
	}

	@Provides
	@ConfigReportTemplateDir
	public String getConfigReportTemplateDir(
			@Named("report.template.dir") String property) {
		return property;
	}
}

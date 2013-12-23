package com.uaijug.certificado.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.uaijug.certificado.config.ConfigEmailCharset;
import com.uaijug.certificado.config.ConfigEmailPassword;
import com.uaijug.certificado.config.ConfigEmailSmtpAuth;
import com.uaijug.certificado.config.ConfigEmailSmtpHost;
import com.uaijug.certificado.config.ConfigEmailSmtpPort;
import com.uaijug.certificado.config.ConfigEmailSmtpTransportType;
import com.uaijug.certificado.config.ConfigEmailStartTlsEnabled;
import com.uaijug.certificado.config.ConfigEmailTextType;
import com.uaijug.certificado.config.ConfigEmailUsername;
import com.uaijug.certificado.model.Configuration;
import com.uaijug.certificado.service.ConfigurationService;

/**
 * Esse módulo é responsável por carregar as configurações do email.
 * 
 * Tenho certeza de que existem soluções melhores, mas estou cansado pra caramba
 * e meu pobre e degenerado cérebro não está conseguindo (e nem querendo) pensar
 * em mais nada. Depois, quando eu fizer as pazes com meus neurônios, eu crio
 * algo decente.
 * 
 * @author Josenaldo
 * 
 */
public class EmailConfigurationModule extends AbstractModule {

	@Override
	protected void configure() {

	}

	private String getConfig(String key,
			ConfigurationService configurationService) {
		Configuration configuration = configurationService.findByKey(key);

		if (configuration != null) {
			return configuration.getValue();
		} else {
			return "";
		}

	}

	@Provides
	@ConfigEmailUsername
	public String getEmailFrom(ConfigurationService configurationService) {
		return this.getConfig("email.username", configurationService);
	}

	@Provides
	@ConfigEmailPassword
	public String getEmailPassword(ConfigurationService configurationService) {
		return this.getConfig("email.password", configurationService);
	}

	@Provides
	@ConfigEmailSmtpHost
	public String getEmailEmailSmtpHost(
			ConfigurationService configurationService) {
		return this.getConfig("email.smtp.host", configurationService);
	}

	@Provides
	@ConfigEmailSmtpPort
	public String getEmailSmtpPort(ConfigurationService configurationService) {
		return this.getConfig("email.smtp.port", configurationService);
	}

	@Provides
	@ConfigEmailSmtpAuth
	public String getEmailSmtpAuth(ConfigurationService configurationService) {
		return this.getConfig("email.smtp.auth", configurationService);
	}

	@Provides
	@ConfigEmailSmtpTransportType
	public String getEmailSmtpTransportType(
			ConfigurationService configurationService) {
		return this
				.getConfig("email.smtp.transport.type", configurationService);
	}

	@Provides
	@ConfigEmailStartTlsEnabled
	public String getEmailStartTlsEnabled(
			ConfigurationService configurationService) {
		return this.getConfig("email.smtp.starttls.enable",
				configurationService);
	}

	@Provides
	@ConfigEmailCharset
	public String EmailCharset(ConfigurationService configurationService) {
		return this.getConfig("email.charset", configurationService);
	}

	@Provides
	@ConfigEmailTextType
	public String getEmailTextType(ConfigurationService configurationService) {
		return this.getConfig("email.text.type", configurationService);
	}

}

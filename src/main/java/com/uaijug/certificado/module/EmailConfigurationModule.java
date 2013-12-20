package com.uaijug.certificado.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.uaijug.certificado.config.ConfigEmailCharset;
import com.uaijug.certificado.config.ConfigEmailFrom;
import com.uaijug.certificado.config.ConfigEmailPassword;
import com.uaijug.certificado.config.ConfigEmailSmtpAuth;
import com.uaijug.certificado.config.ConfigEmailSmtpHost;
import com.uaijug.certificado.config.ConfigEmailStartTlsEnabled;
import com.uaijug.certificado.config.ConfigEmailTextType;

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
		this.bind(String.class).annotatedWith(ConfigEmailFrom.class)
				.toInstance("");

		this.bind(String.class).annotatedWith(ConfigEmailPassword.class)
				.toInstance("");

		this.bind(String.class).annotatedWith(ConfigEmailSmtpAuth.class)
				.toInstance("true");

		this.bind(String.class).annotatedWith(ConfigEmailStartTlsEnabled.class)
				.toInstance("true");

		this.bind(String.class).annotatedWith(ConfigEmailCharset.class)
				.toInstance("UTF-8");

		this.bind(String.class).annotatedWith(ConfigEmailTextType.class)
				.toInstance("text/html");
	}

	@Provides
	@ConfigEmailSmtpHost
	public String getEmailFrom() {
		return null;
	}

}

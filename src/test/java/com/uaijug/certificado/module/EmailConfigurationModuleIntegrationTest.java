package com.uaijug.certificado.module;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.jukito.UseModules;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.uaijug.certificado.config.ConfigEmailCharset;
import com.uaijug.certificado.config.ConfigEmailPassword;
import com.uaijug.certificado.config.ConfigEmailSmtpAuth;
import com.uaijug.certificado.config.ConfigEmailSmtpHost;
import com.uaijug.certificado.config.ConfigEmailSmtpPort;
import com.uaijug.certificado.config.ConfigEmailStartTlsEnabled;
import com.uaijug.certificado.config.ConfigEmailTextType;
import com.uaijug.certificado.config.ConfigEmailUsername;
import com.uaijug.certificado.test.AbstractIntegrationTest;
import com.uaijug.certificado.test.annotation.config.TestBasicConfigurationModule;
import com.uaijug.certificado.test.annotation.type.IntegrationTest;

@Category(IntegrationTest.class)
@UseModules(value = { TestBasicConfigurationModule.class,
		EmailConfigurationModule.class, RepositoryModule.class })
public class EmailConfigurationModuleIntegrationTest extends
		AbstractIntegrationTest {

	@Inject
	@ConfigEmailUsername
	private String username;

	@Inject
	@ConfigEmailPassword
	private String emailPassword;

	@Inject
	@ConfigEmailSmtpHost
	private String emailSmtpHost;

	@Inject
	@ConfigEmailSmtpPort
	private String emailSmtpPort;

	@Inject
	@ConfigEmailSmtpAuth
	private String emailSmtpAuth;

	@Inject
	@ConfigEmailStartTlsEnabled
	private String emailSmtpStarttlsEnable;

	@Inject
	@ConfigEmailCharset
	private String emailCharset;

	@Inject
	@ConfigEmailTextType
	private String emailTextType;

	@Test
	public void testConfigurationEmailUsername() {

		assertThat("A configuração @ConfigEmailUsername não deveria ser nula",
				this.username, is(notNullValue()));
		assertThat("A configuração @ConfigEmailUsername não deveria ser vazia",
				this.username.isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailPassword() {
		assertThat("A configuração @ConfigEmailPassword não deveria ser nula",
				this.emailPassword, is(notNullValue()));
		assertThat("A configuração @ConfigEmailPassword não deveria ser vazia",
				this.emailPassword.isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailSmtpHost() {
		assertThat("A configuração @ConfigEmailSmtpHost não deveria ser nula",
				this.emailSmtpHost, is(notNullValue()));
		assertThat("A configuração @ConfigEmailSmtpHost não deveria ser vazia",
				this.emailSmtpHost.isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailSmtpPort() {
		assertThat("A configuração @ConfigEmailSmtpPort não deveria ser nula",
				this.emailSmtpPort, is(notNullValue()));
		assertThat("A configuração @ConfigEmailSmtpPort não deveria ser vazia",
				this.emailSmtpPort.isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailSmtpAuth() {
		assertThat("A configuração @ConfigEmailSmtpAuth não deveria ser nula",
				this.emailSmtpAuth, is(notNullValue()));
		assertThat("A configuração @ConfigEmailSmtpAuth não deveria ser vazia",
				this.emailSmtpAuth.isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailStartTlsEnabled() {
		assertThat(
				"A configuração @ConfigEmailStartTlsEnabled não deveria ser nula",
				this.emailSmtpStarttlsEnable, is(notNullValue()));
		assertThat(
				"A configuração @ConfigEmailStartTlsEnabled não deveria ser vazia",
				this.emailSmtpStarttlsEnable.isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailCharset() {
		assertThat("A configuração @ConfigEmailCharset não deveria ser nula",
				this.emailCharset, is(notNullValue()));
		assertThat("A configuração @ConfigEmailCharset não deveria ser vazia",
				this.emailCharset.isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailTextType() {
		assertThat("A configuração @ConfigEmailTextType não deveria ser nula",
				this.emailTextType, is(notNullValue()));
		assertThat("A configuração @ConfigEmailTextType não deveria ser vazia",
				this.emailTextType.isEmpty(), is(false));
	}

}

package com.uaijug.certificado.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.jukito.UseModules;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.uaijug.certificado.exception.CannotSendEmailException;
import com.uaijug.certificado.module.EmailConfigurationModule;
import com.uaijug.certificado.module.RepositoryModule;
import com.uaijug.certificado.test.AbstractIntegrationTest;
import com.uaijug.certificado.test.annotation.config.RealTestBasicConfigurationModule;
import com.uaijug.certificado.test.annotation.type.IntegrationTest;

@Category(IntegrationTest.class)
@UseModules(value = { RealTestBasicConfigurationModule.class,
		RepositoryModule.class, EmailConfigurationModule.class })
public class RealEmailServiceIntegrationTest extends AbstractIntegrationTest {

	@Inject
	private EmailService emailService;

	@Test
	public void testConfigurationEmailUsername() {

		assertThat("A configuração @ConfigEmailUsername não deveria ser nula",
				this.emailService.getUsername(), is(notNullValue()));
		assertThat("A configuração @ConfigEmailUsername não deveria ser vazia",
				this.emailService.getUsername().isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailPassword() {
		assertThat("A configuração @ConfigEmailPassword não deveria ser nula",
				this.emailService.getPassword(), is(notNullValue()));
		assertThat("A configuração @ConfigEmailPassword não deveria ser vazia",
				this.emailService.getPassword().isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailSmtpHost() {
		assertThat("A configuração @ConfigEmailSmtpHost não deveria ser nula",
				this.emailService.getSmtpHost(), is(notNullValue()));
		assertThat("A configuração @ConfigEmailSmtpHost não deveria ser vazia",
				this.emailService.getSmtpHost().isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailSmtpPort() {
		assertThat("A configuração @ConfigEmailSmtpPort não deveria ser nula",
				this.emailService.getSmtpPort(), is(notNullValue()));
		assertThat("A configuração @ConfigEmailSmtpPort não deveria ser vazia",
				this.emailService.getSmtpPort().isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailSmtpAuth() {
		assertThat("A configuração @ConfigEmailSmtpAuth não deveria ser nula",
				this.emailService.getSmtpAuth(), is(notNullValue()));
		assertThat("A configuração @ConfigEmailSmtpAuth não deveria ser vazia",
				this.emailService.getSmtpAuth().isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailSmtpTransportType() {
		assertThat(
				"A configuração @ConfigEmailSmtpTransportType não deveria ser nula",
				this.emailService.getSmtpTransportType(), is(notNullValue()));
		assertThat(
				"A configuração @ConfigEmailSmtpTransportType não deveria ser vazia",
				this.emailService.getSmtpTransportType().isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailStartTlsEnabled() {
		assertThat(
				"A configuração @ConfigEmailStartTlsEnabled não deveria ser nula",
				this.emailService.getStarttlsEnable(), is(notNullValue()));
		assertThat(
				"A configuração @ConfigEmailStartTlsEnabled não deveria ser vazia",
				this.emailService.getStarttlsEnable().isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailCharset() {
		assertThat("A configuração @ConfigEmailCharset não deveria ser nula",
				this.emailService.getCharset(), is(notNullValue()));
		assertThat("A configuração @ConfigEmailCharset não deveria ser vazia",
				this.emailService.getCharset().isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailTextType() {
		assertThat("A configuração @ConfigEmailTextType não deveria ser nula",
				this.emailService.getTextType(), is(notNullValue()));
		assertThat("A configuração @ConfigEmailTextType não deveria ser vazia",
				this.emailService.getTextType().isEmpty(), is(false));
	}

	@Test
	public void testSendMessage() throws CannotSendEmailException {

		this.emailService.sendMail("josenaldo@gmail.com", "Teste",
				"Testando email");

	}
}

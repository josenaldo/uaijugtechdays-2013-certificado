package com.uaijug.certificado.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Iterator;

import javax.inject.Inject;

import org.jukito.UseModules;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import com.uaijug.certificado.exception.CannotSendEmailException;
import com.uaijug.certificado.module.RepositoryModule;
import com.uaijug.certificado.test.AbstractIntegrationTest;
import com.uaijug.certificado.test.annotation.type.IntegrationTest;
import com.uaijug.certificado.test.module.TestPropertiesConfigModule;

@Category(IntegrationTest.class)
@UseModules(value = { TestPropertiesConfigModule.class, RepositoryModule.class })
public class EmailServiceIntegrationTest extends AbstractIntegrationTest {

	@Inject
	private EmailService emailService;

	@Before
	public void init() {
		emailService.getPassword().isEmpty();
	}

	@Test
	public void testConfigurationEmailUsername() {

		assertThat("A configuração @ConfigEmailUsername não deveria ser nula",
				emailService.getUsername(), is(notNullValue()));
		assertThat("A configuração @ConfigEmailUsername não deveria ser vazia",
				emailService.getUsername().isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailPassword() {
		String password = emailService.getPassword();
		assertThat("A configuração @ConfigEmailPassword não deveria ser nula",
				password, is(notNullValue()));
		assertThat("A configuração @ConfigEmailPassword não deveria ser vazia",
				password.isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailSmtpHost() {
		assertThat("A configuração @ConfigEmailSmtpHost não deveria ser nula",
				emailService.getSmtpHost(), is(notNullValue()));
		assertThat("A configuração @ConfigEmailSmtpHost não deveria ser vazia",
				emailService.getSmtpHost().isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailSmtpPort() {
		assertThat("A configuração @ConfigEmailSmtpPort não deveria ser nula",
				emailService.getSmtpPort(), is(notNullValue()));
		assertThat("A configuração @ConfigEmailSmtpPort não deveria ser vazia",
				emailService.getSmtpPort().isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailSmtpAuth() {
		assertThat("A configuração @ConfigEmailSmtpAuth não deveria ser nula",
				emailService.getSmtpAuth(), is(notNullValue()));
		assertThat("A configuração @ConfigEmailSmtpAuth não deveria ser vazia",
				emailService.getSmtpAuth().isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailSmtpTransportType() {
		assertThat(
				"A configuração @ConfigEmailSmtpTransportType não deveria ser nula",
				emailService.getSmtpTransportType(), is(notNullValue()));
		assertThat(
				"A configuração @ConfigEmailSmtpTransportType não deveria ser vazia",
				emailService.getSmtpTransportType().isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailStartTlsEnabled() {
		assertThat(
				"A configuração @ConfigEmailStartTlsEnabled não deveria ser nula",
				emailService.getStarttlsEnable(), is(notNullValue()));
		assertThat(
				"A configuração @ConfigEmailStartTlsEnabled não deveria ser vazia",
				emailService.getStarttlsEnable().isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailCharset() {
		assertThat("A configuração @ConfigEmailCharset não deveria ser nula",
				emailService.getCharset(), is(notNullValue()));
		assertThat("A configuração @ConfigEmailCharset não deveria ser vazia",
				emailService.getCharset().isEmpty(), is(false));
	}

	@Test
	public void testConfigurationEmailTextType() {
		assertThat("A configuração @ConfigEmailTextType não deveria ser nula",
				emailService.getTextType(), is(notNullValue()));
		assertThat("A configuração @ConfigEmailTextType não deveria ser vazia",
				emailService.getTextType().isEmpty(), is(false));
	}

	@Test
	public void testSendMessage() throws CannotSendEmailException {
		SimpleSmtpServer server = SimpleSmtpServer.start(10025);

		emailService.sendMail("josenaldo@gmail.com", "Teste", "Testando email");

		server.stop();

		// assertTrue(server.getReceivedEmailSize() == 1);
		assertThat("O servidor deveria ter recebido um email",
				server.getReceivedEmailSize(), is(1));

		Iterator<?> emailIter = server.getReceivedEmail();

		SmtpMessage email = (SmtpMessage) emailIter.next();

		// assertTrue(email.getHeaderValue("Subject").equals("Test"));
		String subject = email.getHeaderValue("Subject");
		assertThat("O assunto da mensagem está errado.", subject,
				is(equalTo("Teste")));

		// assertTrue(email.getBody().equals("Test Body"));
		String body = email.getBody();
		boolean contains = body.contains("Testando email");
		assertThat("O corpor da mensagem está errado.", contains,
				is(equalTo(true)));
	}
}

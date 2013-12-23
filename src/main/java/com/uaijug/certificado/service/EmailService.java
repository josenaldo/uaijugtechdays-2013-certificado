package com.uaijug.certificado.service;

import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.uaijug.certificado.config.ConfigEmailCharset;
import com.uaijug.certificado.config.ConfigEmailPassword;
import com.uaijug.certificado.config.ConfigEmailSmtpAuth;
import com.uaijug.certificado.config.ConfigEmailSmtpHost;
import com.uaijug.certificado.config.ConfigEmailSmtpPort;
import com.uaijug.certificado.config.ConfigEmailSmtpTransportType;
import com.uaijug.certificado.config.ConfigEmailStartTlsEnabled;
import com.uaijug.certificado.config.ConfigEmailTextType;
import com.uaijug.certificado.config.ConfigEmailUsername;
import com.uaijug.certificado.exception.CannotSendEmailException;

/**
 * O EmailService permite o envio de emails através de SMTP. As configurações do
 * servidor devem ser descritas em módulos do Guice e injetadas de acordo com a
 * anotação usada.
 */
@Singleton
public class EmailService {

	/** O campo from do email. */
	@Inject
	@ConfigEmailUsername
	private String username;

	/** A senha da conta de email usada para enviar. */
	@Inject
	@ConfigEmailPassword
	private String password;

	/** Indicador de uso de autenticação SMTP. */
	@Inject
	@ConfigEmailSmtpAuth
	private String smtpAuth;

	/** Indicador de ativação do comando starttls, do servidor de email. */
	@Inject
	@ConfigEmailStartTlsEnabled
	private String starttlsEnable;

	/** O endereço do servidor SMTP. */
	@Inject
	@ConfigEmailSmtpHost
	private String smtpHost;

	/** A porta do servidor SMTP. */
	@Inject
	@ConfigEmailSmtpPort
	private String smtpPort;

	@Inject
	@ConfigEmailSmtpTransportType
	private String smtpTransportType;

	/** O charset da mensagem. No caso de html é necessário especificar. */
	@Inject
	@ConfigEmailCharset
	private String charset;

	/** O tipo do formato da mensagem, texto ou html. */
	@Inject
	@ConfigEmailTextType
	private String textType;

	public void sendMail(String to, String subject, String messageText)
			throws CannotSendEmailException {

		Properties props = this.configureEmailProperties();

		// Criando sessão
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					@Override
					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								EmailService.this.username,
								EmailService.this.password);
					}
				});

		try {
			// Criando mensagem
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(this.username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject(subject);

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(messageText, this.textType);
			Multipart mps = new MimeMultipart();
			mps.addBodyPart(mimeBodyPart);

			message.setContent(mps);

			// criando conexão
			Transport transport = session.getTransport(this.smtpTransportType);
			transport.connect(this.smtpHost, this.username, this.password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (MessagingException e) {
			throw new CannotSendEmailException(
					"Não foi possível enviar o email", e);
		}
	}

	private Properties configureEmailProperties() {
		Properties props = new Properties();

		props.put("mail.smtp.auth", this.smtpAuth);
		props.put("mail.smtp.starttls.enable", this.starttlsEnable);
		props.put("mail.smtp.host", this.smtpHost);
		props.put("mail.smtp.port", this.smtpPort);
		props.put("mail.mime.charset", this.charset);
		return props;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public String getSmtpHost() {
		return this.smtpHost;
	}

	public String getSmtpPort() {
		return this.smtpPort;
	}

	public String getSmtpAuth() {
		return this.smtpAuth;
	}

	public String getSmtpTransportType() {
		return this.smtpTransportType;
	}

	public String getStarttlsEnable() {
		return this.starttlsEnable;
	}

	public String getCharset() {
		return this.charset;
	}

	public String getTextType() {
		return this.textType;
	}

}

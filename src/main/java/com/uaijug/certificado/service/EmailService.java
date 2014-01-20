package com.uaijug.certificado.service;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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
	private Integer smtpPort;

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

	/**
	 * @param to
	 * @param subject
	 * @param messageText
	 * @throws CannotSendEmailException
	 */
	public void sendMail(String to, String subject, String messageText)
			throws CannotSendEmailException {
		this.sendMail(to, subject, messageText, null);
	}

	public void sendMail(String to, String subject, String messageText,
			String attachement) throws CannotSendEmailException {

		Properties props = configureEmailProperties();

		// Criando sessão

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					@Override
					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Criando mensagem
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject(subject);

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(messageText, textType);

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			if (attachement != null) {
				File fileAttachment = new File(attachement);

				if (!fileAttachment.exists()) {
					throw new IllegalArgumentException(
							"Arquivo a ser anexo não existe. Informe o arquivo correto. Caminho inválido: "
									+ fileAttachment.getAbsolutePath());
				}

				DataSource source = new FileDataSource(fileAttachment);

				messageBodyPart = new MimeBodyPart();

				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(fileAttachment.getName());
				multipart.addBodyPart(messageBodyPart);
			}

			message.setContent(multipart);

			// criando conexão
			Transport transport = session.getTransport(smtpTransportType);
			transport.connect(smtpHost, smtpPort, username, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (MessagingException e) {
			throw new CannotSendEmailException(
					"Não foi possível enviar o email", e);
		}
	}

	private Properties configureEmailProperties() {
		Properties props = new Properties();

		props.put("mail.smtp.user", username);
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", smtpPort.toString());
		props.put("mail.smtp.auth", smtpAuth);
		props.put("mail.smtp.starttls.enable", starttlsEnable);
		props.put("mail.mime.charset", charset);
		// props.put("mail.smtp.debug", "true");
		// props.put("mail.smtp.socketFactory.port", this.smtpPort.toString());
		// props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		// props.put("mail.smtp.socketFactory.fallback", "false");
		return props;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public Integer getSmtpPort() {
		return smtpPort;
	}

	public String getSmtpAuth() {
		return smtpAuth;
	}

	public String getSmtpTransportType() {
		return smtpTransportType;
	}

	public String getStarttlsEnable() {
		return starttlsEnable;
	}

	public String getCharset() {
		return charset;
	}

	public String getTextType() {
		return textType;
	}

}

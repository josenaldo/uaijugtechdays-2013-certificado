package com.uaijug.certificado.controller.command;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;

import com.uaijug.certificado.config.ConfigEmailMessageParticipant;
import com.uaijug.certificado.controller.Command;
import com.uaijug.certificado.model.Participant;
import com.uaijug.certificado.report.ParticipantReportGenerator;
import com.uaijug.certificado.service.EmailService;
import com.uaijug.certificado.service.ParticipantService;

public class ParticipantReportCommand implements Command {
	public static final String COMMAND_NAME = "participantReport";

	@Inject
	private ParticipantService participantService;

	@Inject
	private EmailService emailService;

	@Inject
	private ParticipantReportGenerator participantReportGenerator;

	@Inject
	@ConfigEmailMessageParticipant
	private String emailMessageParticipant;

	@Override
	public void execute(Map<String, Object> params) throws Exception {

		String generatedReportPath = null;

		// carregar lista de participantes
		List<Participant> participants = participantService.findAll();

		// para cada participante
		for (Participant participant : participants) {

			// gerar relatorio
			generatedReportPath = participantReportGenerator
					.generate(participant);

			// preparar email
			String emailMessage = prepareEmailMessage(participant);

			// mandar relatorio por email
			emailService.sendMail(participant.getEmail(),
					"UaiJUG techDays 2013 - Certificado", emailMessage,
					generatedReportPath);

		}

	}

	public String prepareEmailMessage(Participant participant)
			throws IOException {
		InputStream resourceAsStream = ParticipantReportCommand.class
				.getClassLoader().getResourceAsStream(emailMessageParticipant);

		List<String> lines = IOUtils.readLines(resourceAsStream,
				Charset.forName("UTF-8"));
		StringBuilder builder = new StringBuilder();

		for (String line : lines) {
			builder.append(line);
		}

		String message = builder.toString();

		return message;
	}

	public void setParticipantReportGenerator(
			ParticipantReportGenerator participantReportGenerator) {
		this.participantReportGenerator = participantReportGenerator;
	}

	public void setParticipantService(ParticipantService participantService) {
		this.participantService = participantService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public void setEmailMessageParticipant(String emailMessageParticipant) {
		this.emailMessageParticipant = emailMessageParticipant;
	}
}

package com.uaijug.certificado.controller.command;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.uaijug.certificado.config.ConfigEmailMessageParticipant;
import com.uaijug.certificado.controller.Command;
import com.uaijug.certificado.exception.CannotSendEmailException;
import com.uaijug.certificado.model.Participant;
import com.uaijug.certificado.report.ParticipantReportGenerator;
import com.uaijug.certificado.service.EmailService;
import com.uaijug.certificado.service.ParticipantService;

public class ParticipantReportCommand implements Command {

	private static final Logger log = Logger
			.getLogger(ParticipantReportCommand.class);

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

		log.debug("Obtendo lista de participantes");
		List<Participant> participants = this.participantService.findAll();

		List<Participant> participantsWithError = new ArrayList<Participant>();

		log.debug("Processando lista de participantes");
		for (Participant participant : participants) {

			try {

				log.debug(participant.getName() + ": gerando relatório");
				generatedReportPath = this.participantReportGenerator
						.generate(participant);

				log.debug(participant.getName() + ": preparando mensagem");
				String emailMessage = this.prepareEmailMessage(participant);

				log.debug(participant.getName() + ": enviando email");
				this.emailService.sendMail(participant.getEmail(),
						"UaiJUG techDays 2013 - Certificado", emailMessage,
						generatedReportPath);
			} catch (CannotSendEmailException e) {
				log.debug(participant.getName()
						+ ": não foi possível enviar o email. Mensagem: ");
				participantsWithError.add(participant);
			} catch (Exception e) {
				log.debug(participant.getName()
						+ ": erro ao processar participante");
				participantsWithError.add(participant);
			}
		}

		log.debug("Participantes com erro =================================================");
		for (Participant participant : participantsWithError) {
			log.debug(participant.getId() + " - " + participant.getName());
		}

	}

	public String prepareEmailMessage(Participant participant)
			throws IOException {
		InputStream resourceAsStream = ParticipantReportCommand.class
				.getClassLoader().getResourceAsStream(
						this.emailMessageParticipant);

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

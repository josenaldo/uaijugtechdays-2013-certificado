package com.uaijug.certificado.controller.command;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

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

	@Override
	public void execute(Map<String, Object> params) throws Exception {

		String generatedReportPath = null;

		// carregar lista de participantes
		List<Participant> participants = this.participantService.findAll();

		// para cada participante
		for (Participant participant : participants) {

			// gerar relatorio
			generatedReportPath = this.participantReportGenerator
					.generate(participant);

			// preparar email
			String emailMessage = this.prepareEmailMessage(participant);

			// mandar relatorio por email
			this.emailService.sendMail(participant.getEmail(),
					"UaiJUG techDays 2013 - Certificado", emailMessage,
					generatedReportPath);

		}

	}

	public String prepareEmailMessage(Participant participant) {
		return "Olha aí seu certificado";
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

}

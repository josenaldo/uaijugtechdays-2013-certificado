package com.uaijug.certificado.controller.command;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.uaijug.certificado.controller.Command;
import com.uaijug.certificado.model.Participant;
import com.uaijug.certificado.report.ParticipantReportGenerator;
import com.uaijug.certificado.service.ParticipantService;

public class ParticipantReportCommand implements Command {
	public static final String COMMAND_NAME = "participantReport";

	@Inject
	private ParticipantService participantService;

	@Inject
	private ParticipantReportGenerator participantReportGenerator;

	@Override
	public void execute(Map<String, Object> params) throws Exception {

		String generatedReportPath = null;

		// carregar lista de participantes
		List<Participant> participants = this.participantService.findAll();

		for (Participant participant : participants) {
			generatedReportPath = this.participantReportGenerator
					.generate(participant);

		}
		// //// para cada participante
		// //// gerar relatorio
		// //// preparar email
		// //// mandar relatorio por email

	}
}

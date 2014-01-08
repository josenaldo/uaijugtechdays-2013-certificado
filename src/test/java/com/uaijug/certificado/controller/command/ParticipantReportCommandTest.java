package com.uaijug.certificado.controller.command;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.uaijug.certificado.model.Participant;
import com.uaijug.certificado.report.ParticipantReportGenerator;
import com.uaijug.certificado.service.EmailService;
import com.uaijug.certificado.service.ParticipantService;

@RunWith(JukitoRunner.class)
public class ParticipantReportCommandTest {

	private ParticipantReportCommand command;

	@Mock
	private ParticipantService participantService;

	@Mock
	private EmailService emailService;

	@Mock
	private ParticipantReportGenerator participantReportGenerator;

	private List<Participant> participants;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		this.participants = Arrays.asList(new Participant("Pedro",
				"pedro@bol.com.br"), new Participant("Mateus",
				"mateus@bol.com.br"),
				new Participant("Joao", "joao@bol.com.br"));
		when(this.participantService.findAll()).thenReturn(this.participants);

		this.command = new ParticipantReportCommand();
		this.command.setEmailService(this.emailService);
		this.command
				.setParticipantReportGenerator(this.participantReportGenerator);
		this.command.setParticipantService(this.participantService);
	}

	@Test
	public void testExecute() throws Exception {

		this.command.execute(null);
		verify(this.participantService).findAll();

		verify(this.participantReportGenerator, times(this.participants.size()))
				.generate(any(Participant.class));

		verify(this.emailService, times(this.participants.size())).sendMail(
				anyString(), anyString(), anyString(), anyString());

		// recuperar lista de participantes
		// criar um certificado para cada um
		// enviar o certificado por email

	}

}

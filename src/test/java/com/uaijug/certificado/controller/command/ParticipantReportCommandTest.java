package com.uaijug.certificado.controller.command;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

		participants = Arrays.asList(new Participant("Pedro",
				"pedro@bol.com.br"), new Participant("Mateus",
				"mateus@bol.com.br"),
				new Participant("Joao", "joao@bol.com.br"));
		when(participantService.findAll()).thenReturn(participants);

		command = new ParticipantReportCommand();
		command.setEmailService(emailService);
		command.setParticipantReportGenerator(participantReportGenerator);
		command.setParticipantService(participantService);
		command.setEmailMessageParticipant("participant-message.html");
	}

	@Test
	public void testExecute() throws Exception {

		command.execute(null);
		verify(participantService).findAll();

		verify(participantReportGenerator, times(participants.size()))
				.generate(any(Participant.class));

		verify(emailService, times(participants.size())).sendMail(anyString(),
				anyString(), anyString(), anyString());

	}

}

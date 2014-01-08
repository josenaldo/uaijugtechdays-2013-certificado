package com.uaijug.certificado.service;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
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
import com.uaijug.certificado.repository.ParticipantRepository;

@RunWith(JukitoRunner.class)
public class ParticipantServiceTest {

	@Mock
	private ParticipantRepository participantRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindAll() {

		List<Participant> participants = Arrays.asList(new Participant("Pedro",
				"pedro@bol.com.br"), new Participant("Mateus",
				"mateus@bol.com.br"),
				new Participant("Joao", "joao@bol.com.br"));
		when(this.participantRepository.findAll()).thenReturn(participants);

		ParticipantService participantService = new ParticipantService();
		participantService.setParticipantRepository(this.participantRepository);

		List<Participant> list = participantService.findAll();
		assertThat("Lista deveria ter 3 elementos", list, hasSize(3));

	}
}

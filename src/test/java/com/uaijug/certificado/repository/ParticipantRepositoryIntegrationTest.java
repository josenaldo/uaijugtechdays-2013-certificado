package com.uaijug.certificado.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.jukito.UseModules;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.uaijug.certificado.model.Participant;
import com.uaijug.certificado.module.RepositoryModule;
import com.uaijug.certificado.test.AbstractIntegrationTest;
import com.uaijug.certificado.test.annotation.type.IntegrationTest;
import com.uaijug.certificado.test.module.TestBasicConfigurationModule;

@Category(IntegrationTest.class)
@UseModules(value = { TestBasicConfigurationModule.class,
		RepositoryModule.class })
public class ParticipantRepositoryIntegrationTest extends
		AbstractIntegrationTest {

	@Inject
	private ParticipantRepository participantRepository;

	@Test
	public void testSave() {
		Participant participant = new Participant();
		participant.setName("Participante anônimo");
		participant.setEmail("participante@uaijug.com.br");

		this.participantRepository.create(participant);

		List<Participant> participants = this.participantRepository.findAll();
		assertThat("Deve haver no mínimo 3 participantes", participants,
				hasSize(3));
	}

	@Test
	public void testFindAll() {
		List<Participant> participants = this.participantRepository.findAll();
		assertThat("Deve haver no mínimo 2 participantes", participants,
				hasSize(2));
	}

	@Test
	public void testDelete() {

		this.participantRepository.delete(1l);

		List<Participant> participants = this.participantRepository.findAll();
		assertThat("Deve haver 1 participante", participants, hasSize(1));

		Participant participant = this.participantRepository.find(1L);
		assertThat("O participante 1 não deveria mais existir", participant,
				is(nullValue()));
	}

	@Test
	public void testUpdate() {
		Participant participant = this.participantRepository.find(1L);
		participant.setName("Participante anônimo");
		participant.setEmail("participante@uaijug.com.br");

		this.participantRepository.update(participant);

		participant = this.participantRepository.find(1L);

		assertThat("Nome do participante está errado", participant.getName(),
				is("Participante anônimo"));
	}
}

package com.uaijug.certificado.service;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.jukito.UseModules;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.uaijug.certificado.model.Participant;
import com.uaijug.certificado.module.BasicConfigurationTestModule;
import com.uaijug.certificado.module.RepositoryModule;
import com.uaijug.certificado.test.AbstractIntegrationTest;
import com.uaijug.certificado.test.annotation.type.IntegrationTest;

@Category(IntegrationTest.class)
@UseModules(value = { BasicConfigurationTestModule.class,
		RepositoryModule.class })
public class ParticipantServiceIntegrationTest extends AbstractIntegrationTest {

	@Inject
	private ParticipantService participantService;

	@Test
	public void testFindAll() {

		List<Participant> list = this.participantService.findAll();
		assertThat("Lista deveria ter 2 elementos", list, hasSize(2));

	}
}

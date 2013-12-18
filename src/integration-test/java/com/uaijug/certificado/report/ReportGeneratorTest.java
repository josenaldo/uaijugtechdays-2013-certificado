package com.uaijug.certificado.report;

import java.io.FileNotFoundException;
import java.util.List;

import javax.inject.Inject;

import net.sf.jasperreports.engine.JRException;

import org.jukito.UseModules;
import org.junit.Test;

import com.uaijug.certificado.model.Participant;
import com.uaijug.certificado.module.BasicConfigurationTestModule;
import com.uaijug.certificado.module.RepositoryModule;
import com.uaijug.certificado.repository.ParticipantRepository;
import com.uaijug.certificado.test.AbstractIntegrationTest;

@UseModules(value = { BasicConfigurationTestModule.class,
		RepositoryModule.class })
public class ReportGeneratorTest extends AbstractIntegrationTest {

	@Inject
	private ReportGenerator reportGenerator;

	@Inject
	private ParticipantRepository participantRepository;

	@Test
	public void test() throws FileNotFoundException, JRException {
		List<Participant> participants = this.participantRepository.findAll();

		this.reportGenerator.generateParticipantReport(participants);
	}

}

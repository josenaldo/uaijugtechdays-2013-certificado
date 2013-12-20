package com.uaijug.certificado.report;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
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
public class ParticipantReportGeneratorTest extends AbstractIntegrationTest {

	@Inject
	private ParticipantReportGenerator reportGenerator;

	@Inject
	private ParticipantRepository participantRepository;

	@Test
	public void test() throws FileNotFoundException, JRException {
		List<Participant> participants = this.participantRepository.findAll();

		for (Participant participant : participants) {
			this.reportGenerator.generate(participant);
		}

		String reportPath = null;
		File reportFile = null;

		for (Participant participant : participants) {
			reportPath = this.reportGenerator.getReportPath(participant);

			reportFile = new File(reportPath);

			assertThat("Arquivo não foi gerado", reportFile.exists(), is(true));
		}

	}

}

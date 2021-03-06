package com.uaijug.certificado.report;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.uaijug.certificado.config.ConfigReportBackgroundPage1;
import com.uaijug.certificado.config.ConfigReportBackgroundPage2;
import com.uaijug.certificado.config.ConfigReportGeneratedDir;
import com.uaijug.certificado.config.ConfigReportParticipantTemplate;
import com.uaijug.certificado.config.ConfigReportTemplateDir;
import com.uaijug.certificado.model.Participant;

@Singleton
public class ParticipantReportGenerator extends
		AbstractReportGenerator<Participant> {

	private static Logger log = Logger
			.getLogger(ParticipantReportGenerator.class);

	@Inject
	@ConfigReportParticipantTemplate
	private String participantReportTemplate;

	@Inject
	@ConfigReportTemplateDir
	private String reportTemplateDir;

	@Inject
	@ConfigReportBackgroundPage1
	private String reportBackgroundPage1;

	@Inject
	@ConfigReportBackgroundPage2
	private String reportBackgroundPage2;

	@Inject
	@ConfigReportGeneratedDir
	private String generatedDir;

	@Override
	protected List<Participant> getCollection(Participant participant) {
		return Arrays.asList(participant);
	}

	@Override
	protected Map<String, Object> getParameters() {
		Map<String, Object> parameters = new HashMap<String, Object>();

		String page1 = this.reportTemplateDir + this.reportBackgroundPage1;
		InputStream page1Stream = this.getResourceInputStream(page1);
		parameters.put("BACKGROUND1", page1Stream);

		String page2 = this.reportTemplateDir + this.reportBackgroundPage2;
		InputStream page2Stream = this.getResourceInputStream(page2);
		parameters.put("BACKGROUND2", page2Stream);
		return parameters;
	}

	@Override
	protected String getTemplatePath() {
		return this.reportTemplateDir + this.participantReportTemplate;
	}

	@Override
	protected String getReportPath(Participant participant) {

		String encodedEmail = Base64.encodeBase64URLSafeString(participant
				.getEmail().getBytes());

		File generated = new File(this.generatedDir);
		if (!generated.exists()) {
			generated.mkdir();
		}

		String reportpath = this.generatedDir + encodedEmail + ".pdf";

		return reportpath;
	}
}

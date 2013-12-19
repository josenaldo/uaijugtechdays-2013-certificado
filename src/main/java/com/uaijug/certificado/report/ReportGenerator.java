package com.uaijug.certificado.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.uaijug.certificado.config.ConfigReportParticipantTemplate;
import com.uaijug.certificado.config.ConfigReportBackgroundPage1;
import com.uaijug.certificado.config.ConfigReportBackgroundPage2;
import com.uaijug.certificado.config.ConfigReportTemplateDir;
import com.uaijug.certificado.model.Participant;

@Singleton
public class ReportGenerator {

	private static Logger log = Logger.getLogger(ReportGenerator.class);

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

	public void generateParticipantReport(Participant participant)
			throws FileNotFoundException, JRException {

		log.debug("Iniciando a exportação do relatório: "
				+ this.participantReportTemplate);

		JasperPrint jasperPrint = null;
		JasperReport jasperReport = null;
		Map<String, Object> parameters = new HashMap<String, Object>();

		log.debug("Compilando template");
		String templatePath = this.reportTemplateDir
				+ this.participantReportTemplate;
		InputStream reportTemplateStream = this
				.getResourceInputStream(templatePath);
		jasperReport = JasperCompileManager.compileReport(reportTemplateStream);

		log.debug("Preparando background da página 1");
		String page1 = this.reportTemplateDir + this.reportBackgroundPage1;
		InputStream page1Stream = this.getResourceInputStream(page1);
		parameters.put("BACKGROUND1", page1Stream);

		log.debug("Preparando background da página 2");
		String page2 = this.reportTemplateDir + this.reportBackgroundPage2;
		InputStream page2Stream = this.getResourceInputStream(page2);
		parameters.put("BACKGROUND2", page2Stream);

		log.debug("Exportando");
		String reportPath = this.getReportPath(participant);

		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
				new JRBeanCollectionDataSource(Arrays.asList(participant)));

		this.exportToPdf(jasperPrint, reportPath);
	}

	private InputStream getResourceInputStream(String filePath) {
		InputStream page2Stream = Thread.currentThread()
				.getContextClassLoader().getResourceAsStream(filePath);
		return page2Stream;
	}

	private void exportToPdf(JasperPrint jasperPrint, String reportName)
			throws FileNotFoundException, JRException {
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				new FileOutputStream(reportName));

		exporter.exportReport();
	}

	String getReportPath(Participant participant) {

		String encodedEmail = Base64.encodeBase64URLSafeString(participant
				.getEmail().getBytes());

		String reportpath = "generated/" + encodedEmail + ".pdf";

		return reportpath;
	}
}

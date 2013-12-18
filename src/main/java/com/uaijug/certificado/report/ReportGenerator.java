package com.uaijug.certificado.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
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
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;

import org.dbunit.util.Base64;

import com.uaijug.certificado.config.ConfigParticipantReportTemplate;
import com.uaijug.certificado.model.Participant;

@Singleton
public class ReportGenerator {

	@Inject
	@ConfigParticipantReportTemplate
	private String participantReportTemplate;

	public void generateParticipantReport(List<Participant> participants)
			throws FileNotFoundException, JRException {

		JasperDesign jasperDesign = null;
		JasperPrint jasperPrint = null;
		JasperReport jasperReport = null;

		Map<String, Object> parameters = new HashMap<String, Object>();

		InputStream reportTemplateStream = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream(this.participantReportTemplate);

		jasperReport = JasperCompileManager.compileReport(reportTemplateStream);

		InputStream page1Stream = Thread.currentThread()
				.getContextClassLoader().getResourceAsStream("page1.jpg");

		parameters.put("BACKGROUND1", page1Stream);

		InputStream page2Stream = Thread.currentThread()
				.getContextClassLoader().getResourceAsStream("page2.jpg");

		parameters.put("BACKGROUND2", page2Stream);

		// jasperDesign = JRXmlLoader.load(reportTemplateStream);

		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
				new JRBeanCollectionDataSource(participants));

		String reportName = null;
		for (Participant participant : participants) {
			reportName = Base64.encodeString(participant.getEmail());
			this.exportToPdf(jasperPrint, reportName);
		}

		JasperViewer.viewReport(jasperPrint);

	}

	private void exportToPdf(JasperPrint jasperPrint, String reportName)
			throws FileNotFoundException, JRException {
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				new FileOutputStream(reportName + ".pdf"));

		exporter.exportReport();
	}
}

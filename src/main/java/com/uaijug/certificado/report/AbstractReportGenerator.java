package com.uaijug.certificado.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import com.uaijug.certificado.model.Participant;

public abstract class AbstractReportGenerator<T> {

	public String generate(T t) throws FileNotFoundException, JRException {

		JasperPrint jasperPrint = null;
		JasperReport jasperReport = null;

		String templatePath = this.getTemplatePath();

		InputStream reportTemplateStream = this
				.getResourceInputStream(templatePath);

		jasperReport = JasperCompileManager.compileReport(reportTemplateStream);

		Map<String, Object> parameters = this.getParameters();

		String reportPath = this.getReportPath(t);

		List<Participant> collection = this.getCollection(t);

		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
				new JRBeanCollectionDataSource(collection));

		this.exportToPdf(jasperPrint, reportPath);

		return reportPath;
	}

	protected abstract List<Participant> getCollection(T t);

	protected abstract String getReportPath(T t);

	protected abstract Map<String, Object> getParameters();

	protected abstract String getTemplatePath();

	protected InputStream getResourceInputStream(String filePath) {
		InputStream inputStream = Thread.currentThread()
				.getContextClassLoader().getResourceAsStream(filePath);
		return inputStream;
	}

	protected void exportToPdf(JasperPrint jasperPrint, String reportName)
			throws FileNotFoundException, JRException {
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

		File file = new File(reportName);
		FileOutputStream outputStream = new FileOutputStream(file);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);

		exporter.exportReport();
	}

}

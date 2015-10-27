package com.metasoft.ibilling.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import com.metasoft.ibilling.controller.vo.TrackingSearchResultVo;

@Service
public class ExporterService {

	public static final String MEDIA_TYPE_EXCEL = "application/vnd.ms-excel";
	public static final String MEDIA_TYPE_PDF = "application/pdf";
	public static final String EXTENSION_TYPE_EXCEL = "xls";
	public static final String EXTENSION_TYPE_EXCEL_XML = "xls";
	public static final String EXTENSION_TYPE_PDF = "pdf";

	public HttpServletResponse export(String type, String fileName, JasperPrint jp, HttpServletResponse response,
			ByteArrayOutputStream baos) {
		fileName += "." + type;
		if (type.equalsIgnoreCase(EXTENSION_TYPE_EXCEL)) {
			// Export to output stream
			exportXls(jp, baos);

			// Set our response properties
			// Here you can declare a custom filename
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

			// Set content type
			response.setContentType(MEDIA_TYPE_EXCEL);
			response.setContentLength(baos.size());

			return response;
		}

		if (type.equalsIgnoreCase(EXTENSION_TYPE_PDF)) {
			// Export to output stream
			exportPdf(jp, baos);

			// Set our response properties
			// Here you can declare a custom filename
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

			// Set content type
			response.setContentType(MEDIA_TYPE_PDF);
			response.setContentLength(baos.size());

			return response;

		}
		
		throw new RuntimeException("No type set for type " + type);
	}

	public void exportXls(JasperPrint jp, ByteArrayOutputStream baos) {
		// Create a JRXlsExporter instance
		JRXlsExporter exporter = new JRXlsExporter();
		SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();

		// Here we assign the parameters jp and baos to the exporter
		exporter.setExporterInput(new SimpleExporterInput(jp));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));

		// Excel specific parameters
		configuration.setOnePagePerSheet(Boolean.FALSE);
		configuration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
		configuration.setWhitePageBackground(Boolean.FALSE);
		configuration.setDetectCellType(Boolean.TRUE);
		configuration.setIgnorePageMargins(Boolean.TRUE);
		configuration.setFontSizeFixEnabled(Boolean.TRUE);
		exporter.setConfiguration(configuration);
		try {
			exporter.exportReport();

		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

	public void exportPdf(JasperPrint jp, ByteArrayOutputStream baos) {
		// Create a JRXlsExporter instance
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(jp));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));

		// Here we assign the parameters jp and baos to the exporter
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		exporter.setConfiguration(configuration);

		try {
			exporter.exportReport();

		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}
	

		 
	

}

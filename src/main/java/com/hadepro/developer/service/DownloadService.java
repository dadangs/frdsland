package com.hadepro.developer.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hadepro.developer.controller.Exporter;
import com.hadepro.developer.dao.SalesDAO;
import com.hadepro.developer.model.Sales;
import com.hadepro.developer.model.Transaksi;
 


/**
 * Service for processing Jasper  reports
 * 
 */
@Service("downloadService")
@Transactional
public class DownloadService {

	protected static Logger logger = Logger.getLogger("controller");

	/**
	 * Processes the download for Excel format
	 */
	@SuppressWarnings("unchecked")
	public   void downloadXLS(Transaksi transaksi, HttpServletResponse response) throws ClassNotFoundException, JRException {

		logger.debug("Downloading Excel report");
		
		// Retrieve our data source
		//SalesDAO datasource = new SalesDAO();
		List<Transaksi> items = new ArrayList<Transaksi>();
		items.add(transaksi);
		JRDataSource ds = new JRBeanCollectionDataSource(items);	
		//JRDataSource ds = datasource.getDataSource();

		// params is used for passing extra parameters 
		HashMap params = new HashMap(); 
		params.put("Title", "Sales Report");
		
		// Retrieve our report template
		InputStream reportStream = this.getClass().getResourceAsStream("/dp.jrxml"); 

		// Create a JasperDesign object from the JRXMl file
		JasperDesign jd = JRXmlLoader.load(reportStream);
		
		// You can also load the template by directly adding the actual path, i.e. 
		//JasperDesign jd = JRXmlLoader.load("c:/krams/jasper/reports/tree-template.jrxml");
		
		// You can also let Spring inject the template
		// See http://stackoverflow.com/questions/734671/read-file-in-classpath
		
		// Compile our report layout
		JasperReport jr = JasperCompileManager.compileReport(jd);

		// Creates the JasperPrint object
		// It needs a JasperReport layout and a datasource
		JasperPrint jp = JasperFillManager.fillReport(jr, params, ds);

		// Create our output byte stream
		// This is the stream where the data will be written
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// Export to output stream
		// The data will be exported to the ByteArrayOutputStream baos
		// We delegate the exporting  to a custom Exporter instance
		// The Exporter is a wrapper class I made. Feel free to remove or modify it
		Exporter exporter = new Exporter();
		exporter.export(jp, baos);

		// Set our response properties
		// Here you can declare a custom filename
		//String fileName = "SalesReport.xls";
		String fileName = "SalesReport.pdf";
		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);
		// Make sure to set the correct content type
		// Each format has its own content type
		//response.setContentType("application/vnd.ms-excel");
		response.setContentType("application/pdf");
		response.setContentLength(baos.size());

		// Write to reponse stream
		 writeReportToResponseStream(response, baos);
		
		/*ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		JRPdfExporter exporter = new JRPdfExporter();   
		JasperExportManager.exportReportToPdfFile(jp,"dp.xls");
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);		 
		exporter.exportReport(); 
		writeReportToResponseStream(response, baos);*/
	}
	
	/**
	 * Writes the report to the output stream
	 */
	private   void writeReportToResponseStream(HttpServletResponse response,
			ByteArrayOutputStream baos) {
		
		logger.debug("Writing report to the stream");
		try {
			// Retrieve the output stream
			ServletOutputStream outputStream = response.getOutputStream();
			// Write to the output stream
			baos.writeTo(outputStream);
			// Flush the stream
			outputStream.flush();

		} catch (Exception e) {
			logger.error("Unable to write report to the output stream");
		}
		
	}

}

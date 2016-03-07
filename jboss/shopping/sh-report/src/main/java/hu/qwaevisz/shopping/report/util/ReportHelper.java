package hu.qwaevisz.shopping.report.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class ReportHelper {

	public ReportHelper() {

	}

	public JasperReport compile(final String jrxmlFileName) throws FileNotFoundException, JRException {
		final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(jrxmlFileName);
		final JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		return JasperCompileManager.compileReport(jasperDesign);
	}

	public JasperPrint fill(final JasperReport jasperReport, final Connection connection, final Map<String, Object> params) throws JRException {
		return JasperFillManager.fillReport(jasperReport, params, connection);
	}

	public byte[] exportToPdf(final JasperPrint jasperPrint) throws JRException {
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

}

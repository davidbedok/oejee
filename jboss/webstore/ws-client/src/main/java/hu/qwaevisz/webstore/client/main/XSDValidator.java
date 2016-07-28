package hu.qwaevisz.webstore.client.main;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XSDValidator {

	private static final String BASEDIR = "..\\ws-ejbservice\\build\\help\\";

	public static void main(String[] args) throws SAXException, IOException {
		SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

		File xsdFile = new File(BASEDIR + "webstore-help.xsd");
		File xmlFile = new File(BASEDIR + "ws-ejbservice-doc.xml");

		Schema schema = factory.newSchema(xsdFile);
		Validator validator = schema.newValidator();

		Source source = new StreamSource(xmlFile);

		validator.validate(source);

		System.out.println("Valid");
	}

}

package hu.qwaevisz.webstore.doclet;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ProcessingInstruction;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.AnnotationDesc.ElementValuePair;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.RootDoc;
import com.sun.tools.doclets.standard.Standard;

import hu.qwaevisz.webstore.common.doclet.Description;

public class WebStoreDoclet extends Standard {

	private static final String NAMESPACE = "urn:hu:qwaevisz:schema:xml:webstore:help:1";
	private static final String OUTPUT_DEFAULT_FILENAME = "webstore.xml";
	private static final String ARGUMENT_PREFIX = "-";
	private static final String OUTPUT_FILENAME_ARGUMENT = ARGUMENT_PREFIX + "ws-filename";
	private static final String ROOT_ELEMENT = "webstore";
	private static final String CLASS_ELEMENT = "service";
	private static final String METHOD_ELEMENT = "operation";
	private static final String PARAMETER_ELEMENT = "argument";
	private static final String NAME_ATTRIBUTE = "name";
	private static final String TYPE_ATTRIBUTE = "type";
	private static final String DESCRIPTION_ATTRIBUTE = "description";

	// Mandatory method
	public static int optionLength(final String option) {
		if (option.equals(OUTPUT_FILENAME_ARGUMENT)) {
			return 2;
		} else if (option.equals("-d")) { // Gradle javadoc
			return 2;
		} else if (option.equals("-doctitle")) { // Gradle javadoc
			return 2;
		} else if (option.equals("-windowtitle")) { // Gradle javadoc
			return 2;
		}
		return 0;
	}

	public static boolean start(final RootDoc root) {
		try {
			final Document doc = createDocument();

			doc.setXmlStandalone(true);
			final ProcessingInstruction pi = doc.createProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\"webstore-help.xsl\"");

			final Element rootElement = doc.createElementNS(NAMESPACE, ROOT_ELEMENT);

			final ClassDoc[] classes = root.classes();
			for (final ClassDoc clazz : classes) {
				final Element element = getClassElement(doc, clazz);
				if (element != null) {
					rootElement.appendChild(element);
				}
			}
			doc.appendChild(rootElement);
			doc.insertBefore(pi, rootElement);

			writeOutput(doc, getOutputFileName(root.options()));
		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	private static Document createDocument() throws ParserConfigurationException {
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.newDocument();
	}

	private static String getOutputFileName(final String[][] options) {
		String result = OUTPUT_DEFAULT_FILENAME;
		for (final String[] argument : options) {
			if (argument.length == 2) {
				final String argumentName = argument[0];
				final String argumentValue = argument[1];
				if (argumentName.equals(OUTPUT_FILENAME_ARGUMENT)) {
					result = argumentValue;
				}
			}
		}
		return result;
	}

	private static Element getClassElement(final Document doc, final ClassDoc clazz) {
		boolean valid = false;
		final Element element = doc.createElementNS(NAMESPACE, CLASS_ELEMENT);
		element.setAttribute(NAME_ATTRIBUTE, clazz.name());

		final String description = getDescription(clazz.annotations());
		if (description != null) {
			valid = true;
			element.setAttribute(DESCRIPTION_ATTRIBUTE, description);
		}

		for (final MethodDoc method : clazz.methods()) {
			final Element methodElement = getMethodElement(doc, method);
			if (methodElement != null) {
				element.appendChild(methodElement);
			}
		}
		return valid ? element : null;
	}

	private static String getDescription(final AnnotationDesc[] annotations) {
		String result = null;
		for (final AnnotationDesc annotation : annotations) {
			if (annotation.annotationType().qualifiedTypeName().equals(Description.class.getName())) {
				for (final ElementValuePair pair : annotation.elementValues()) {
					if (pair.element().name().equals("value")) {
						result = pair.value().value().toString();
						break;
					}
				}
			}
		}
		return result;
	}

	private static Element getMethodElement(final Document doc, final MethodDoc method) {
		boolean valid = false;
		final Element element = doc.createElementNS(NAMESPACE, METHOD_ELEMENT);
		element.setAttribute(NAME_ATTRIBUTE, method.name());

		final String description = getDescription(method.annotations());
		if (description != null) {
			valid = true;
			element.setAttribute(DESCRIPTION_ATTRIBUTE, description);
		}

		for (final Parameter parameter : method.parameters()) {
			element.appendChild(getParameterElement(doc, parameter));
		}

		return valid ? element : null;
	}

	private static Element getParameterElement(final Document doc, final Parameter parameter) {
		final Element element = doc.createElementNS(NAMESPACE, PARAMETER_ELEMENT);
		element.setAttribute(NAME_ATTRIBUTE, parameter.name());
		element.setAttribute(TYPE_ATTRIBUTE, parameter.typeName());

		final String description = getDescription(parameter.annotations());
		if (description != null) {
			element.setTextContent(description);
		}
		return element;
	}

	private static void writeOutput(final Document doc, final String outputFileName) throws TransformerException {
		final TransformerFactory transformerFactory = TransformerFactory.newInstance();
		transformerFactory.setAttribute("indent-number", 2);
		final Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

		final DOMSource source = new DOMSource(doc);
		final StreamResult result = new StreamResult(new File(outputFileName));
		transformer.transform(source, result);

		final StreamResult consoleResult = new StreamResult(System.out);
		transformer.transform(source, consoleResult);
	}

}

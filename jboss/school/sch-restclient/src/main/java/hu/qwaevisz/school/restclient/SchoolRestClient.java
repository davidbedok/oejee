package hu.qwaevisz.school.restclient;

import java.net.URI;
import java.util.logging.Logger;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.ClientRequestFactory;
import org.jboss.resteasy.client.ClientResponse;

import hu.qwaevisz.school.restclient.domain.MarkCriteria;
import hu.qwaevisz.school.restclient.domain.MarkStub;

public class SchoolRestClient {

	private static final Logger LOGGER = Logger.getLogger(SchoolRestClient.class.getSimpleName());

	private static final String SERVICE_CONTEXT_PATH = "/school/api";

	private final String host;
	private final int port;

	public SchoolRestClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public MarkStub process(String studentNeptun, MarkCriteria criteria) {
		final URI serviceUri = UriBuilder.fromUri(this.getServicePath()).build();
		final ClientRequestFactory crf = new ClientRequestFactory(serviceUri);

		final MarkRestService api = crf.createProxy(MarkRestService.class);
		final ClientResponse<MarkStub> response = api.getMarks(studentNeptun, criteria);

		LOGGER.info("Response status: " + response.getStatus());
		final MultivaluedMap<String, Object> header = response.getMetadata();
		for (final String key : header.keySet()) {
			LOGGER.info("HEADER - key: " + key + ", value: " + header.get(key));
		}
		final MarkStub entity = response.getEntity();
		LOGGER.info("Response entity: " + entity);
		return entity;
	}

	private String getServicePath() {
		return "http://" + this.host + ":" + this.port + SERVICE_CONTEXT_PATH;
	}

}

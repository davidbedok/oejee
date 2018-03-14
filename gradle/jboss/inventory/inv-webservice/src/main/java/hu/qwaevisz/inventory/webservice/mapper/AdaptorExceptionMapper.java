package hu.qwaevisz.inventory.webservice.mapper;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;
import hu.qwaevisz.inventory.webservice.filter.InventoryCORSFilter;

@Provider
public class AdaptorExceptionMapper implements ExceptionMapper<AdaptorException> {

	@Context
	private HttpHeaders headers;

	@Override
	public Response toResponse(AdaptorException e) {
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e) //
				.header(InventoryCORSFilter.ALLOW_ORIGIN, "*") //
				.header(InventoryCORSFilter.ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS, HEAD") //
				.header(InventoryCORSFilter.MAX_AGE, "1209600") //
				.header(InventoryCORSFilter.ALLOW_HEADERS, "x-requested-with, origin, content-type, accept, X-Codingpedia, authorization") //
				.header(InventoryCORSFilter.ALLOW_CREDENTIALS, "true") //
				.type(MediaType.APPLICATION_JSON).build();
	}

}

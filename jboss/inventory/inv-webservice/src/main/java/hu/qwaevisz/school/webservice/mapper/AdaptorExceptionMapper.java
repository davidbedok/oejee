package hu.qwaevisz.school.webservice.mapper;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;
import hu.qwaevisz.school.webservice.filter.InventoryCrossOriginRequestFilter;

@Provider
public class AdaptorExceptionMapper implements ExceptionMapper<AdaptorException> {

	@Context
	private HttpHeaders headers;

	@Override
	public Response toResponse(AdaptorException e) {
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.build()) //
				.header(InventoryCrossOriginRequestFilter.ALLOW_ORIGIN, "*") //
				.header(InventoryCrossOriginRequestFilter.ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS, HEAD") //
				.header(InventoryCrossOriginRequestFilter.MAX_AGE, "1209600") //
				.header(InventoryCrossOriginRequestFilter.ALLOW_HEADERS, "x-requested-with, origin, content-type, accept, X-Codingpedia, authorization") //
				.header(InventoryCrossOriginRequestFilter.ALLOW_CREDENTIALS, "true") //
				.type(MediaType.APPLICATION_JSON).build();
	}

}

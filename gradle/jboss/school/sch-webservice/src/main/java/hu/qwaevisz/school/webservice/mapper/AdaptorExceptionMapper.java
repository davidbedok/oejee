package hu.qwaevisz.school.webservice.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import hu.qwaevisz.school.ejbservice.exception.AdaptorException;
import hu.qwaevisz.school.webservice.filter.SchoolCORSFilter;

@Provider
public class AdaptorExceptionMapper implements ExceptionMapper<AdaptorException> {

	@Override
	public Response toResponse(final AdaptorException e) {
		return Response.status(e.getHttpStatus()).entity(e.build()) //
				.header(SchoolCORSFilter.ALLOW_ORIGIN, "*") //
				.header(SchoolCORSFilter.ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS, HEAD") //
				.header(SchoolCORSFilter.MAX_AGE, "1209600") //
				.header(SchoolCORSFilter.ALLOW_HEADERS, "x-requested-with, origin, content-type, accept, X-Codingpedia, authorization") //
				.header(SchoolCORSFilter.ALLOW_CREDENTIALS, "true") //
				.type(MediaType.APPLICATION_JSON).build();
	}

}

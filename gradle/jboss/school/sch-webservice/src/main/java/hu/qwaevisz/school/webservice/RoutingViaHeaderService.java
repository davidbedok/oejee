package hu.qwaevisz.school.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import hu.qwaevisz.school.ejbservice.domain.NumberStub;

@Path("/routing")
public interface RoutingViaHeaderService {

	@GET
	@Consumes("text/plain")
	@Produces("application/json")
	@Path("/number")
	NumberStub getNumberTextContentTypeJsonAccept();

	@GET
	@Consumes("text/plain")
	@Produces("application/xml")
	@Path("/number")
	NumberStub getNumberTextContentTypeXmlAccept();

	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/number")
	NumberStub getNumberJsonContentType();

	@GET
	@Consumes("application/xml")
	@Produces("application/json")
	@Path("/number")
	NumberStub getNumberXmlContentType();

}

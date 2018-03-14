package hu.qwaevisz.inventory.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import hu.qwaevisz.inventory.ejbservice.domain.InternationalInventoryItemBean;
import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;

@Path("/international")
public interface InternationalRestService {

	@GET
	@Path("/{reference}")
	@Produces(MediaType.APPLICATION_JSON)
	InternationalInventoryItemBean getInventoryStub(@PathParam("reference") String reference) throws AdaptorException;

	@GET
	@Path("/notify/{reference}")
	@Produces(MediaType.APPLICATION_JSON)
	InternationalInventoryItemBean getInventoryStubWithEvents(@PathParam("reference") String reference) throws AdaptorException;

	@OPTIONS
	@Path("{path:.*}")
	Response optionsAll(@PathParam("path") String path);

}

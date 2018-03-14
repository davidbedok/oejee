package hu.qwaevisz.inventory.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;

@Path("/configuration")
public interface ConfigurationRestService {

	@GET
	@Path("/numbers/{quantity}")
	@Produces(MediaType.APPLICATION_JSON)
	List<Integer> getRandomNumbers(@PathParam("quantity") int quantity) throws AdaptorException;

	@GET
	@Path("/host")
	String getHost() throws AdaptorException;

	@OPTIONS
	@Path("{path:.*}")
	Response optionsAll(@PathParam("path") String path);

}

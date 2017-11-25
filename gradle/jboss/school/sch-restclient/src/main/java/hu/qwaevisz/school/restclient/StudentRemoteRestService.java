package hu.qwaevisz.school.restclient;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.jboss.resteasy.client.ClientResponse;

import hu.qwaevisz.school.restclient.domain.MarkConditions;
import hu.qwaevisz.school.restclient.domain.MarkStub;

@Path("/student")
public interface StudentRemoteRestService {

	@POST
	@Consumes("application/xml")
	@Produces("application/xml")
	@Path("/marks/{student}")
	@Wrapped(element = "marks")
	ClientResponse<List<MarkStub>> getFilteredMarks(@PathParam("student") String neptun, MarkConditions conditions);

}

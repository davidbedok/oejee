package hu.qwaevisz.school.webservice;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import hu.qwaevisz.school.ejbservice.domain.StudentStub;
import hu.qwaevisz.school.ejbservice.exception.AdaptorException;

@Path("/student")
public interface StudentRestService {

	@GET
	@Path("/{neptun}")
	@Produces("application/json")
	StudentStub getStudent(@PathParam("neptun") String neptun) throws AdaptorException;

	@GET
	@Path("/list")
	@Produces("application/json")
	List<StudentStub> getAllStudent() throws AdaptorException;

	@DELETE
	@Path("/{neptun}")
	void removeStudent(@PathParam("neptun") String neptun) throws AdaptorException;

	@OPTIONS
	@Path("{path:.*}")
	Response optionsAll(@PathParam("path") String path);

}

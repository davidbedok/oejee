package hu.qwaevisz.school.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import hu.qwaevisz.school.ejbservice.domain.MarkCriteria;
import hu.qwaevisz.school.ejbservice.domain.MarkStub;
import hu.qwaevisz.school.ejbservice.domain.StudentStub;
import hu.qwaevisz.school.ejbservice.exception.AdaptorException;

@Path("/student")
public interface StudentRestService {

	@GET
	@Path("/{neptun}")
	@Produces(MediaType.APPLICATION_JSON)
	StudentStub getStudent(@PathParam("neptun") String neptun) throws AdaptorException;

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	List<StudentStub> getAllStudents() throws AdaptorException;

	@GET
	@Path("/list/{page}")
	@Produces(MediaType.APPLICATION_JSON)
	Response getStudents(@DefaultValue("3") @QueryParam("pagesize") int pageSize, @PathParam("page") int page) throws AdaptorException;

	@DELETE
	@Path("/{neptun}")
	void removeStudent(@PathParam("neptun") String neptun) throws AdaptorException;

	@POST
	@Consumes("application/xml")
	@Produces("application/xml")
	@Path("/marks/{neptun}")
	@Wrapped(element = "marks")
	List<MarkStub> getMarks(@PathParam("neptun") String neptun, MarkCriteria criteria) throws AdaptorException;

	@OPTIONS
	@Path("{path:.*}")
	Response optionsAll(@PathParam("path") String path);

}

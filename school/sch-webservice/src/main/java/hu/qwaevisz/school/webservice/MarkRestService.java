package hu.qwaevisz.school.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import hu.qwaevisz.school.ejbservice.domain.MarkCriteria;
import hu.qwaevisz.school.ejbservice.domain.MarkDetailStub;
import hu.qwaevisz.school.ejbservice.domain.MarkInputStub;
import hu.qwaevisz.school.ejbservice.domain.MarkStub;
import hu.qwaevisz.school.ejbservice.exception.AdaptorException;

@Path("/mark")
public interface MarkRestService {

	@POST
	@Path("/stat")
	@Produces("application/json")
	List<MarkDetailStub> getMarkDetails(String subject) throws AdaptorException;

	@PUT
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	MarkStub addMark(MarkInputStub stub) throws AdaptorException;

	@POST
	@Consumes("application/xml")
	@Produces("application/xml")
	@Path("/get/{studentneptun}")
	MarkStub getMatchingMark(@PathParam("studentneptun") String studentNeptun, MarkCriteria criteria) throws AdaptorException;

	@OPTIONS
	@Path("{path:.*}")
	Response optionsAll(@PathParam("path") String path);

}

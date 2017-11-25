package hu.qwaevisz.school.webservice;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import hu.qwaevisz.school.ejbservice.domain.MarkCriteria;
import hu.qwaevisz.school.ejbservice.domain.MarkStub;
import hu.qwaevisz.school.ejbservice.domain.StudentStub;
import hu.qwaevisz.school.ejbservice.exception.AdaptorException;
import hu.qwaevisz.school.ejbservice.facade.MarkFacade;
import hu.qwaevisz.school.ejbservice.facade.StudentFacade;

@Stateless
public class StudentRestServiceBean implements StudentRestService {

	private static final Logger LOGGER = Logger.getLogger(StudentRestServiceBean.class);

	@EJB
	private StudentFacade facade;

	@EJB
	private MarkFacade markFacade;

	@Override
	public StudentStub getStudent(final String neptun) throws AdaptorException {
		LOGGER.info("Get Student (" + neptun + ")");
		return this.facade.getStudent(neptun);
	}

	@Override
	public List<StudentStub> getAllStudents() throws AdaptorException {
		LOGGER.info("Get all Students");
		return this.facade.getAllStudents();
	}

	@Override
	public Response getStudents(int pageSize, int page) throws AdaptorException {
		List<StudentStub> students = this.facade.getStudents(pageSize, page);
		return Response.status(Status.OK).entity(students).type(MediaType.APPLICATION_JSON).build();
	}

	@Override
	public void removeStudent(final String neptun) throws AdaptorException {
		LOGGER.info("Remove Student (" + neptun + ")");
		this.facade.removeStudent(neptun);
	}

	@Override
	public List<MarkStub> getMarks(String neptun, MarkCriteria criteria) throws AdaptorException {
		LOGGER.info("Get matching Marks (neptun: " + neptun + ", criteria: " + criteria + ")");
		return this.markFacade.getMarks(neptun, criteria.getSubjectNameTerm(), criteria.getMinimumGrade(), criteria.getMaximumGrade());
	}

	@Override
	public Response optionsAll(final String path) {
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}

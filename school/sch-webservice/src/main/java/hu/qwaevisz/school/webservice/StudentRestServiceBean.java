package hu.qwaevisz.school.webservice;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import hu.qwaevisz.school.ejbservice.domain.StudentStub;
import hu.qwaevisz.school.ejbservice.exception.AdaptorException;
import hu.qwaevisz.school.ejbservice.facade.StudentFacade;

@Stateless
public class StudentRestServiceBean implements StudentRestService {

	private static final Logger LOGGER = Logger.getLogger(StudentRestServiceBean.class);

	@EJB
	private StudentFacade facade;

	@Override
	public StudentStub getStudent(String neptun) throws AdaptorException {
		LOGGER.info("Get Student (" + neptun + ")");
		return this.facade.getStudent(neptun);
	}

	@Override
	public List<StudentStub> getAllStudent() throws AdaptorException {
		LOGGER.info("Get all Students");
		return this.facade.getAllStudents();
	}

	@Override
	public void removeStudent(String neptun) throws AdaptorException {
		LOGGER.info("Remove Student (" + neptun + ")");
		this.facade.removeStudent(neptun);
	}

	@Override
	public Response optionsAll(String path) {
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}

package hu.qwaevisz.school.webservice;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import hu.qwaevisz.school.ejbservice.domain.MarkDetailStub;
import hu.qwaevisz.school.ejbservice.domain.MarkInputStub;
import hu.qwaevisz.school.ejbservice.domain.MarkStub;
import hu.qwaevisz.school.ejbservice.exception.AdaptorException;
import hu.qwaevisz.school.ejbservice.facade.MarkFacade;

@Stateless
public class MarkRestServiceBean implements MarkRestService {

	private static final Logger LOGGER = Logger.getLogger(MarkRestServiceBean.class);

	@EJB
	private MarkFacade facade;

	@Override
	public List<MarkDetailStub> getMarkDetails(String subject) throws AdaptorException {
		LOGGER.info("Get MarkDetails (" + subject + ")");
		return this.facade.getMarkDetails(subject);
	}

	@Override
	public MarkStub addMark(MarkInputStub stub) throws AdaptorException {
		LOGGER.info("Add Mark (" + stub + ")");
		return this.facade.addMark(stub.getSubject(), stub.getNeptun(), stub.getGrade().getValue(), stub.getNote());
	}

	@Override
	public Response optionsAll(String path) {
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}

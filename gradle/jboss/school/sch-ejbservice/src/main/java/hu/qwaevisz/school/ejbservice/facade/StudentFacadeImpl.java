package hu.qwaevisz.school.ejbservice.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;

import hu.qwaevisz.school.ejbservice.converter.StudentConverter;
import hu.qwaevisz.school.ejbservice.domain.StudentStub;
import hu.qwaevisz.school.ejbservice.exception.AdaptorException;
import hu.qwaevisz.school.ejbservice.util.ApplicationError;
import hu.qwaevisz.school.persistence.exception.PersistenceServiceException;
import hu.qwaevisz.school.persistence.service.MarkService;
import hu.qwaevisz.school.persistence.service.StudentService;

@Stateless(mappedName = "ejb/studentFacade")
public class StudentFacadeImpl implements StudentFacade {

	private static final Logger LOGGER = Logger.getLogger(StudentFacadeImpl.class);

	@EJB
	private StudentService studentService;

	@EJB
	private MarkService markService;

	@EJB
	private StudentConverter converter;

	@Override
	public StudentStub getStudent(final String neptun) throws AdaptorException {
		try {
			final StudentStub stub = this.converter.to(this.studentService.read(neptun));
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Get Student (neptun: " + neptun + ") --> " + stub);
			}
			return stub;
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new AdaptorException(ApplicationError.UNEXPECTED, e.getLocalizedMessage());
		}
	}

	@Override
	public List<StudentStub> getAllStudents() throws AdaptorException {
		List<StudentStub> stubs = new ArrayList<>();
		try {
			stubs = this.converter.to(this.studentService.readAll());
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Get all Students --> " + stubs.size() + " item(s)");
			}
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new AdaptorException(ApplicationError.UNEXPECTED, e.getLocalizedMessage());
		}
		return stubs;
	}

	@Override
	public List<StudentStub> getStudents(int pageSize, int page) throws AdaptorException {
		List<StudentStub> stubs = new ArrayList<>();
		try {
			stubs = this.converter.to(this.studentService.read(pageSize, page));
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Get all Students --> " + stubs.size() + " item(s)");
			}
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new AdaptorException(ApplicationError.UNEXPECTED, e.getLocalizedMessage());
		}
		return stubs;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void removeStudent(final String neptun) throws AdaptorException {
		try {
			if (this.studentService.exists(neptun)) {
				if (this.markService.count(neptun) == 0) {
					this.studentService.delete(neptun);
				} else {
					throw new AdaptorException(ApplicationError.HAS_DEPENDENCY, "Student has undeleted mark(s)", neptun);
				}
			} else {
				throw new AdaptorException(ApplicationError.NOT_EXISTS, "Student doesn't exist", neptun);
			}
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new AdaptorException(ApplicationError.UNEXPECTED, e.getLocalizedMessage());
		}
	}

}

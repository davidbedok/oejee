package hu.qwaevisz.school.ejbservice.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hu.qwaevisz.school.ejbservice.converter.MarkConverter;
import hu.qwaevisz.school.ejbservice.domain.MarkDetailStub;
import hu.qwaevisz.school.ejbservice.domain.MarkStub;
import hu.qwaevisz.school.ejbservice.exception.AdaptorException;
import hu.qwaevisz.school.ejbservice.util.ApplicationError;
import hu.qwaevisz.school.persistence.exception.PersistenceServiceException;
import hu.qwaevisz.school.persistence.service.MarkService;
import hu.qwaevisz.school.persistence.service.StudentService;
import hu.qwaevisz.school.persistence.service.SubjectService;

@Stateless(mappedName = "ejb/markFacade")
public class MarkFacadeImpl implements MarkFacade {

	private static final Logger LOGGER = Logger.getLogger(MarkFacadeImpl.class);

	@EJB
	private StudentService studentService;

	@EJB
	private SubjectService subjectService;

	@EJB
	private MarkService markService;

	@EJB
	private MarkConverter converter;

	@Override
	public List<MarkDetailStub> getMarkDetails(String subject) throws AdaptorException {
		List<MarkDetailStub> stubs = new ArrayList<>();
		try {
			final Long subjectId = this.subjectService.read(subject).getId();
			stubs = this.converter.to(this.markService.read(subjectId));
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Get all MarkDetails --> " + stubs.size() + " item(s)");
			}
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new AdaptorException(ApplicationError.UNEXPECTED, e.getLocalizedMessage());
		}
		return stubs;
	}

	@Override
	public MarkStub addMark(String subject, String neptun, int grade, String note) throws AdaptorException {
		try {
			final Long subjectId = this.subjectService.read(subject).getId();
			final Long studentId = this.studentService.read(neptun).getId();
			final MarkStub mark = this.converter.to(this.markService.create(studentId, subjectId, grade, note));
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Add a new Mark (subject: " + subject + ", neptun: " + neptun + ", grade: " + grade + ", note: " + note + ") --> " + mark);
			}
			return mark;
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new AdaptorException(ApplicationError.UNEXPECTED, e.getLocalizedMessage());
		}
	}

	@Override
	public MarkStub getMatchingMark(String studentNeptun, String subjectNameTerm, int minimumGrade, int maximumGrade) throws AdaptorException {
		MarkStub stub = null;
		try {
			stub = this.converter.to(this.markService.read(studentNeptun, subjectNameTerm, minimumGrade, maximumGrade));
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Matching Mark (studentNeptun: " + studentNeptun + ", subjectNameTerm: " + subjectNameTerm + ", minimumGrade: " + minimumGrade
						+ ", maximumGrade: " + maximumGrade + ") --> " + stub);
			}
		} catch (PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new AdaptorException(ApplicationError.UNEXPECTED, e.getLocalizedMessage());
		}
		return stub;
	}

}

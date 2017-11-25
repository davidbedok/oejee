package hu.qwaevisz.school.persistence.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import hu.qwaevisz.school.persistence.entity.Mark;
import hu.qwaevisz.school.persistence.entity.Student;
import hu.qwaevisz.school.persistence.entity.Subject;
import hu.qwaevisz.school.persistence.entity.view.MarkDetail;
import hu.qwaevisz.school.persistence.exception.PersistenceServiceException;
import hu.qwaevisz.school.persistence.parameter.MarkDetailParameter;
import hu.qwaevisz.school.persistence.parameter.MarkParameter;
import hu.qwaevisz.school.persistence.result.MarkDetailResult;

@Stateless(mappedName = "ejb/markService")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MarkServiceImpl implements MarkService {

	private static final Logger LOGGER = Logger.getLogger(MarkServiceImpl.class);

	@PersistenceContext(unitName = "sch-persistence-unit")
	private EntityManager entityManager;

	@EJB
	private StudentService studentService;

	@EJB
	private SubjectService subjectService;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int count(final String studentNeptun) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Count Student's marks by student's neptun (" + studentNeptun + ")");
		}
		try {
			return this.entityManager.createNamedQuery(Mark.COUNT_BY_STUDENT_NEPTUN, Long.class).setParameter(MarkParameter.STUDENT_NEPTUN, studentNeptun)
					.getSingleResult().intValue();
		} catch (final Exception e) {
			throw new PersistenceServiceException(
					"Unknown error during counting Student's mark by student's neptun (" + studentNeptun + ")! " + e.getLocalizedMessage(), e);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Mark create(final Long studentId, final Long subjectId, final Integer grade, final String note) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Add Mark (studentId: " + studentId + ", subjectId: " + subjectId + ", grade: " + grade + ", note: " + note + ")");
		}
		try {
			final Student student = this.entityManager.find(Student.class, studentId);
			final Subject subject = this.entityManager.find(Subject.class, subjectId);
			Mark mark = new Mark(student, subject, grade, note);
			this.entityManager.persist(mark);
			this.entityManager.flush();
			return mark;
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error during persisting Mark (studentId: " + studentId + ", subjectId: " + subjectId + ", grade: "
					+ grade + ", note: " + note + ")! " + e.getLocalizedMessage(), e);
		}
	}

	@Override
	public List<MarkDetailResult> read(String subjectName) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get all MarkDetailResult by subject (" + subjectName + ")");
		}
		List<MarkDetailResult> result = null;
		try {
			result = this.entityManager.createNamedQuery(MarkDetail.GET_AVG_MARKDETAILS, MarkDetailResult.class)
					.setParameter(MarkDetailParameter.SUBJECT, subjectName).getResultList();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Students! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public List<Mark> read(final String studentNeptun, final String subjectNameTerm, final Integer minimumGrade, final Integer maximumGrade)
			throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get matching Mark(s) by criteria (studentNeptun: " + studentNeptun + ", subjectNameTerm: " + subjectNameTerm + ", minimumGrade: "
					+ minimumGrade + ", maximumGrade: " + maximumGrade + ")");
		}
		List<Mark> result = null;
		try {
			result = this.entityManager.createNamedQuery(Mark.READ_BY_FILTER, Mark.class).setParameter(MarkParameter.STUDENT_NEPTUN, studentNeptun)
					.setParameter(MarkParameter.SUBJECT_NAME_TERM, subjectNameTerm).setParameter(MarkParameter.MIN_GRADE, minimumGrade)
					.setParameter(MarkParameter.MAX_GRADE, maximumGrade).getResultList();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching matching Mark (studentNeptun: " + studentNeptun + ", subjectNameTerm: "
					+ subjectNameTerm + ", minimumGrade: " + minimumGrade + ", maximumGrade: " + maximumGrade + ")! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

}

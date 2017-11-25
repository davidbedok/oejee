package hu.qwaevisz.school.persistence.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import hu.qwaevisz.school.persistence.entity.Student;
import hu.qwaevisz.school.persistence.exception.PersistenceServiceException;
import hu.qwaevisz.school.persistence.parameter.StudentParameter;

@Stateless(mappedName = "ejb/studentService")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class StudentServiceImpl implements StudentService {

	private static final Logger LOGGER = Logger.getLogger(StudentServiceImpl.class);

	@PersistenceContext(unitName = "sch-persistence-unit")
	private EntityManager entityManager;

	@EJB
	private MarkService markService;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean exists(final String neptun) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Check Student by neptun (" + neptun + ")");
		}
		try {
			return this.entityManager.createNamedQuery(Student.COUNT_BY_NAME, Long.class).setParameter(StudentParameter.NEPTUN, neptun).getSingleResult() == 1;
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error during counting Students by neptun (" + neptun + ")! " + e.getLocalizedMessage(), e);
		}
	}

	@Override
	public Student read(final Long id) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Student by id (" + id + ")");
		}
		Student result = null;
		try {
			result = this.entityManager.createNamedQuery(Student.GET_BY_ID, Student.class).setParameter(StudentParameter.ID, id).getSingleResult();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Student by id (" + id + ")! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Student read(final String neptun) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Student by neptun (" + neptun + ")");
		}
		Student result = null;
		try {
			result = this.entityManager.createNamedQuery(Student.GET_BY_NEPTUN, Student.class).setParameter(StudentParameter.NEPTUN, neptun).getSingleResult();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Student by neptun (" + neptun + ")! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public List<Student> readAll() throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get all Students");
		}
		List<Student> result = null;
		try {
			result = this.entityManager.createNamedQuery(Student.GET_ALL, Student.class).getResultList();
			for (final Student student : result) {
				student.getMarks().size();
			}
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Students! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public List<Student> read(int pageSize, int page) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Students (pageSize: " + pageSize + ", page: " + page + ")");
		}
		List<Student> result = null;
		try {
			result = this.entityManager.createNamedQuery(Student.GET_ALL, Student.class).setFirstResult((page - 1) * pageSize).setMaxResults(pageSize)
					.getResultList();
			List<Long> studentIds = result.stream().map(Student::getId).collect(Collectors.toList());
			result = this.entityManager.createNamedQuery(Student.GET_BY_IDS, Student.class).setParameter("ids", studentIds).getResultList();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Students! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(final String neptun) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Remove Student by neptun (" + neptun + ")");
		}
		try {
			this.entityManager.createNamedQuery(Student.REMOVE_BY_NEPTUN).setParameter(StudentParameter.NEPTUN, neptun).executeUpdate();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when removing Student by neptun (" + neptun + ")! " + e.getLocalizedMessage(), e);
		}
	}

}

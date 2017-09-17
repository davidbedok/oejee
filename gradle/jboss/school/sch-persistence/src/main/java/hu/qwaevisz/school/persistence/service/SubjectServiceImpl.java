package hu.qwaevisz.school.persistence.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import hu.qwaevisz.school.persistence.entity.Subject;
import hu.qwaevisz.school.persistence.exception.PersistenceServiceException;
import hu.qwaevisz.school.persistence.parameter.SubjectParameter;
import hu.qwaevisz.school.persistence.query.SubjectQuery;

@Stateless(mappedName = "ejb/subjectService")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class SubjectServiceImpl implements SubjectService {

	private static final Logger LOGGER = Logger.getLogger(SubjectServiceImpl.class);

	@PersistenceContext(unitName = "sch-persistence-unit")
	private EntityManager entityManager;

	@Override
	public Subject read(Long id) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Subject by id (" + id + ")");
		}
		Subject result = null;
		try {
			result = this.entityManager.createNamedQuery(SubjectQuery.GET_BY_ID, Subject.class).setParameter(SubjectParameter.ID, id).getSingleResult();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Subject by id (" + id + ")! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public Subject read(String name) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Subject by name (" + name + ")");
		}
		Subject result = null;
		try {
			result = this.entityManager.createNamedQuery(SubjectQuery.GET_BY_NAME, Subject.class).setParameter(SubjectParameter.NAME, name).getSingleResult();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Subject by name (" + name + ")! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

}

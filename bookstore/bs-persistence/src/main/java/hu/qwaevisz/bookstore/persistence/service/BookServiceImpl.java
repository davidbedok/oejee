package hu.qwaevisz.bookstore.persistence.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import hu.qwaevisz.bookstore.persistence.entity.Book;
import hu.qwaevisz.bookstore.persistence.exception.PersistenceServiceException;
import hu.qwaevisz.bookstore.persistence.parameter.BookParameter;
import hu.qwaevisz.bookstore.persistence.query.BookQuery;

@Stateless(mappedName = "ejb/groupService")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class BookServiceImpl implements BookService {

	private static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class);

	@PersistenceContext(unitName = "bs-persistence-unit")
	private EntityManager entityManager;

	@Override
	public Book read(Long id) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Book by id (" + id + ")");
		}
		Book result = null;
		try {
			result = this.entityManager.createNamedQuery(BookQuery.GET_BY_ID, Book.class).setParameter(BookParameter.ID, id).getSingleResult();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Book by id (" + id + ")! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public Book read(String isbn) throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Book by ISBN (" + isbn + ")");
		}
		Book result = null;
		try {
			result = this.entityManager.createNamedQuery(BookQuery.GET_BY_ISBN, Book.class).setParameter(BookParameter.ISBN, isbn).getSingleResult();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Book by ISBN (" + isbn + ")! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public List<Book> readAll() throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get Books");
		}
		List<Book> result = null;
		try {
			result = this.entityManager.createNamedQuery(BookQuery.GET_ALL, Book.class).getResultList();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Books! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

}

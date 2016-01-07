package hu.qwaevisz.bookstore.persistence.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import hu.qwaevisz.bookstore.persistence.entity.Book;
import hu.qwaevisz.bookstore.persistence.entity.trunk.BookCategory;
import hu.qwaevisz.bookstore.persistence.exception.PersistenceServiceException;
import hu.qwaevisz.bookstore.persistence.parameter.BookParameter;
import hu.qwaevisz.bookstore.persistence.query.BookQuery;

@Stateless(mappedName = "ejb/groupService")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class BookServiceImpl implements BookService {

	private static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class.getName());

	@PersistenceContext(unitName = "bs-persistence-unit")
	private EntityManager entityManager;

	@Override
	public boolean exists(String isbn) throws PersistenceServiceException {
		if (this.LOGGER.isLoggable(Level.FINE) ) {
			LOGGER.fine("Check Book by ISBN (" + isbn + ")");
		}
		try {
			return this.entityManager.createNamedQuery(BookQuery.COUNT_BY_ISBN, Long.class).setParameter(BookParameter.ISBN, isbn)
					.getSingleResult() == 1;
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error during counting Books by ISBN (" + isbn + ")! " + e.getLocalizedMessage(), e);
		}
	}

	@Override
	public Book create(String isbn, String author, String title, int numberOfPages, double price, BookCategory category) throws PersistenceServiceException {
		if (this.LOGGER.isLoggable(Level.FINE) ) {
			this.LOGGER.fine("Create Book (isbn: "+isbn+", author: "+author+", title: "+title+", numberOfPages: "+numberOfPages+", price: "+price+ ", category: " + category + ")");
		}
		try {
			Book book = new Book(isbn, author, title, numberOfPages, price, category);
			this.entityManager.persist(book);
			return book;
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error during persisting Book (" + isbn + ")! " + e.getLocalizedMessage(), e);
		}
	}

	@Override
	public Book read(Long id) throws PersistenceServiceException {
		if (this.LOGGER.isLoggable(Level.FINE) ) {
			this.LOGGER.fine("Get Book by id (" + id + ")");
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
		if (this.LOGGER.isLoggable(Level.FINE) ) {
			this.LOGGER.fine("Get Book by ISBN (" + isbn + ")");
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
		if (this.LOGGER.isLoggable(Level.FINE) ) {
			this.LOGGER.fine("Get Books");
		}
		List<Book> result = null;
		try {
			result = this.entityManager.createNamedQuery(BookQuery.GET_ALL, Book.class).getResultList();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Books! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public List<Book> read(BookCategory category) throws PersistenceServiceException {
		if (this.LOGGER.isLoggable(Level.FINE) ) {
			this.LOGGER.fine("Get Books by Category");
		}
		List<Book> result = null;
		try {
			result = this.entityManager.createNamedQuery(BookQuery.GET_ALL_BY_CATEGORY, Book.class).setParameter(BookParameter.CATEGORY, category)
					.getResultList();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when fetching Books! " + e.getLocalizedMessage(), e);
		}
		return result;
	}

	@Override
	public Book update(String isbn, String author, String title, int numberOfPages, double price, BookCategory category) throws PersistenceServiceException {
		if (this.LOGGER.isLoggable(Level.FINE) ) {
			this.LOGGER.fine("Update Book (isbn: "+isbn+", author: "+author+", title: "+title+", numberOfPages: "+numberOfPages+", price: "+price+ ", category: " + category + ")");
		}
		try {
			Book book = this.read(isbn);
			book.setAuthor(author);
			book.setTitle(title);
			book.setNumberOfPages(numberOfPages);
			book.setPrice(price);
			book.setCategory(category);
			return this.entityManager.merge(book);
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when mergning Book! " + e.getLocalizedMessage(), e);
		}
	}

	@Override
	public void delete(String isbn) throws PersistenceServiceException {
		if (this.LOGGER.isLoggable(Level.FINE) ) {
			LOGGER.fine("Remove Book by ISBN (" + isbn + ")");
		}
		try {
			this.entityManager.createNamedQuery(BookQuery.REMOVE_BY_ISBN).setParameter(BookParameter.ISBN, isbn).executeUpdate();
		} catch (final Exception e) {
			throw new PersistenceServiceException("Unknown error when removing Book by ISBN (" + isbn + ")! " + e.getLocalizedMessage(), e);
		}
	}

}

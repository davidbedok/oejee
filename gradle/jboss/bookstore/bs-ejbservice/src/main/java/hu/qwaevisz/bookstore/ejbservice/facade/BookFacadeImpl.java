package hu.qwaevisz.bookstore.ejbservice.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hu.qwaevisz.bookstore.ejbservice.converter.BookConverter;
import hu.qwaevisz.bookstore.ejbservice.domain.BookCategoryStub;
import hu.qwaevisz.bookstore.ejbservice.domain.BookCriteria;
import hu.qwaevisz.bookstore.ejbservice.domain.BookStub;
import hu.qwaevisz.bookstore.ejbservice.exception.FacadeException;
import hu.qwaevisz.bookstore.persistence.entity.Book;
import hu.qwaevisz.bookstore.persistence.entity.trunk.BookCategory;
import hu.qwaevisz.bookstore.persistence.exception.PersistenceServiceException;
import hu.qwaevisz.bookstore.persistence.service.BookService;

@Stateless(mappedName = "ejb/bookFacade")
public class BookFacadeImpl implements BookFacade {

	private static final Logger LOGGER = Logger.getLogger(BookFacadeImpl.class);

	@EJB
	private BookService service;

	@EJB
	private BookConverter converter;

	@Override
	public BookStub getBook(String isbn) throws FacadeException {
		try {
			final BookStub stub = this.converter.to(this.service.read(isbn));
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Get Book by isbn (" + isbn + ") --> " + stub);
			}
			return stub;
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new FacadeException(e.getLocalizedMessage());
		}
	}

	@Override
	public List<BookStub> getBooks(BookCriteria criteria) throws FacadeException {
		List<BookStub> stubs = new ArrayList<BookStub>();
		try {
			List<Book> books = null;
			if (criteria.getCategory() == null) {
				books = this.service.readAll();
			} else {
				books = this.service.read(BookCategory.valueOf(criteria.getCategory().name()));
			}
			stubs = this.converter.to(books);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Get Books by criteria (" + criteria + ") --> " + stubs.size() + " book(s)");
			}
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new FacadeException(e.getLocalizedMessage());
		}
		return stubs;
	}

	@Override
	public BookStub saveBook(String isbn, String author, String title, int numberOfPages, double price, BookCategoryStub category) throws FacadeException {
		try {
			Book book = null;
			if (this.service.exists(isbn)) {
				book = this.service.update(isbn, author, title, numberOfPages, price, BookCategory.valueOf(category.name()));
			} else {
				book = this.service.create(isbn, author, title, numberOfPages, price, BookCategory.valueOf(category.name()));
			}
			return this.converter.to(book);
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new FacadeException(e.getLocalizedMessage());
		}
	}

	@Override
	public void removeBook(String isbn) throws FacadeException {
		try {
			this.service.delete(isbn);
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new FacadeException(e.getLocalizedMessage());
		}
	}

}

package hu.qwaevisz.bookstore.ejbservice.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hu.qwaevisz.bookstore.ejbservice.converter.BookConverter;
import hu.qwaevisz.bookstore.ejbservice.domain.BookCriteria;
import hu.qwaevisz.bookstore.ejbservice.domain.BookStub;
import hu.qwaevisz.bookstore.ejbservice.exception.FacadeException;
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
		List<BookStub> stubs = new ArrayList<>();
		try {
			stubs = this.converter.to(this.service.readAll());
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Get Books by criteria (" + criteria + ") --> " + stubs.size() + " book(s)");
			}
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new FacadeException(e.getLocalizedMessage());
		}
		return stubs;
	}

}

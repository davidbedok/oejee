package hu.qwaevisz.booklight.ejbservice.facade;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.booklight.ejbservice.domain.BookCriteria;
import hu.qwaevisz.booklight.ejbservice.domain.BookStub;
import hu.qwaevisz.booklight.ejbservice.exception.FacadeException;

@Local
public interface BookFacade {

	BookStub getBook(String isbn) throws FacadeException;

	List<BookStub> getBooks(BookCriteria criteria) throws FacadeException;

}

package hu.qwaevisz.bookstore.ejbservice.facade;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.bookstore.ejbservice.domain.BookCategoryStub;
import hu.qwaevisz.bookstore.ejbservice.domain.BookCriteria;
import hu.qwaevisz.bookstore.ejbservice.domain.BookStub;
import hu.qwaevisz.bookstore.ejbservice.exception.FacadeException;

@Local
public interface BookFacade {

	BookStub getBook(String isbn) throws FacadeException;

	List<BookStub> getBooks(BookCriteria criteria) throws FacadeException;

	BookStub saveBook(String isbn, String author, String title, int numberOfPages, double price, BookCategoryStub category ) throws FacadeException;

	void removeBook(String isbn) throws FacadeException;

}

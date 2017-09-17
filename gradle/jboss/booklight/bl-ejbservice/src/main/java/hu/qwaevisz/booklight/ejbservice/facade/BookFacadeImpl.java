package hu.qwaevisz.booklight.ejbservice.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import hu.qwaevisz.booklight.ejbservice.domain.BookCategoryStub;
import hu.qwaevisz.booklight.ejbservice.domain.BookCriteria;
import hu.qwaevisz.booklight.ejbservice.domain.BookStub;
import hu.qwaevisz.booklight.ejbservice.exception.FacadeException;

@Stateless(mappedName = "ejb/bookFacade")
public class BookFacadeImpl implements BookFacade {

	@Override
	public BookStub getBook(String isbn) throws FacadeException {
		return new BookStub(isbn, "Frank Herbert", "Dune", BookCategoryStub.SCIFI, 3500, 896);
	}

	@Override
	public List<BookStub> getBooks(BookCriteria criteria) throws FacadeException {
		List<BookStub> stubs = new ArrayList<BookStub>();
		stubs.add(new BookStub("978-0441172719", "Frank Herbert", "Dune", BookCategoryStub.SCIFI, 3500, 896));
		stubs.add(new BookStub("978-0684824710", "Daniel C. Dennett", "Darwin's dangerous idea", BookCategoryStub.PHILOSOPHY, 4500, 586));
		return stubs;
	}

}

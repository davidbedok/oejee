package hu.qwaevisz.bookstore.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.bookstore.ejbservice.domain.BookStub;
import hu.qwaevisz.bookstore.persistence.entity.Book;

@Local
public interface BookConverter {

	BookStub to(Book book);

	List<BookStub> to(List<Book> books);

}

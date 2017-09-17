package hu.qwaevisz.bookstore.ejbservice.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import hu.qwaevisz.bookstore.ejbservice.domain.BookCategoryStub;
import hu.qwaevisz.bookstore.ejbservice.domain.BookStub;
import hu.qwaevisz.bookstore.persistence.entity.Book;

@Stateless
public class BookConverterImpl implements BookConverter {

	@Override
	public BookStub to(Book book) {
		final BookCategoryStub category = BookCategoryStub.valueOf(book.getCategory().toString());
		return new BookStub(book.getIsbn(), book.getAuthor(), book.getTitle(), category, book.getPrice(), book.getNumberOfPages());
	}

	@Override
	public List<BookStub> to(List<Book> books) {
		final List<BookStub> result = new ArrayList<BookStub>();
		for (final Book book : books) {
			result.add(this.to(book));
		}
		return result;
	}

}

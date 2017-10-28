package hu.qwaevisz.bookstore.persistence.service;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.bookstore.persistence.entity.Book;
import hu.qwaevisz.bookstore.persistence.entity.trunk.BookCategory;
import hu.qwaevisz.bookstore.persistence.exception.PersistenceServiceException;

@Local
public interface BookSearch {

	List<Book> find(String isbn, String author, String title, Integer numberOfPagesMin, Integer numberOfPagesMax, Double maxPrice, BookCategory category)
			throws PersistenceServiceException;

}

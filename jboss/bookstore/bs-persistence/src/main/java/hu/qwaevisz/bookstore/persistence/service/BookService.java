package hu.qwaevisz.bookstore.persistence.service;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.bookstore.persistence.entity.Book;
import hu.qwaevisz.bookstore.persistence.exception.PersistenceServiceException;

@Local
public interface BookService {

	Book read(Long id) throws PersistenceServiceException;

	Book read(String isbn) throws PersistenceServiceException;

	List<Book> readAll() throws PersistenceServiceException;

}

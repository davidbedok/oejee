package hu.qwaevisz.bookstore.persistence.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import hu.qwaevisz.bookstore.persistence.entity.Book;
import hu.qwaevisz.bookstore.persistence.entity.trunk.BookCategory;
import hu.qwaevisz.bookstore.persistence.exception.PersistenceServiceException;

@Stateless(mappedName = "ejb/bookSearch")
public class BookSearchImpl implements BookSearch {

	private static final Logger LOGGER = Logger.getLogger(BookSearchImpl.class);

	@PersistenceContext(unitName = "bs-persistence-unit")
	private EntityManager entityManager;

	void setEntityManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public List<Book> find(String isbn, String author, String title, Integer numberOfPagesMin, Integer numberOfPagesMax, Double maxPrice, BookCategory category)
			throws PersistenceServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Find Books from criteria");
		}
		List<Book> result = new ArrayList<>();

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<Book> query = builder.createQuery(Book.class);
		Root<Book> book = query.from(Book.class);
		query.select(book);

		if (isbn != null) {
			query.where(builder.equal(book.get("isbn"), isbn));
		}
		if (author != null) {
			query.where(builder.like(book.get("author"), author + "%"));
		}
		if (title != null) {
			query.where(builder.like(book.get("title"), title + "%"));
		}
		if (numberOfPagesMin != null && numberOfPagesMin > 0 && numberOfPagesMax != null && numberOfPagesMax > 0) {
			query.where(builder.between(book.get("numberOfPages"), numberOfPagesMin, numberOfPagesMax));
		}
		if (maxPrice != null && maxPrice > 0) {
			query.where(builder.lessThanOrEqualTo(book.get("price"), maxPrice));
		}
		if (category != null) {
			query.where(builder.equal(book.get("category"), category));
		}

		query.orderBy(builder.asc(book.get("title")));

		result = this.entityManager.createQuery(query).getResultList();

		return result;
	}

}

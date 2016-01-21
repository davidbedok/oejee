package hu.qwaevisz.bookstore.weblayer.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import hu.qwaevisz.bookstore.ejbservice.domain.BookCategoryStub;
import hu.qwaevisz.bookstore.ejbservice.domain.BookCriteria;
import hu.qwaevisz.bookstore.ejbservice.domain.BookStub;
import hu.qwaevisz.bookstore.ejbservice.exception.FacadeException;
import hu.qwaevisz.bookstore.ejbservice.facade.BookFacade;
import hu.qwaevisz.bookstore.weblayer.common.FormValue;
import hu.qwaevisz.bookstore.weblayer.common.ListAttribute;
import hu.qwaevisz.bookstore.weblayer.common.ListParameter;
import hu.qwaevisz.bookstore.weblayer.common.Page;

@WebServlet("/BookList")
public class BookListController extends HttpServlet implements ListAttribute, ListParameter, FormValue {

	private static final long serialVersionUID = -1977646750178615187L;

	private static final Logger LOGGER = Logger.getLogger(BookListController.class);

	@EJB
	private BookFacade facade;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Get All Books");
		try {
			final List<BookStub> books = this.facade.getBooks(new BookCriteria());
			request.setAttribute(ATTR_BOOKS, books);
		} catch (final FacadeException e) {
			LOGGER.error(e, e);
		}
		this.forward(request, response, new BookCriteria(), FILTER_ALL_CATEGORY);
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String categoryName = request.getParameter(CATEGORY);
		final BookCriteria criteria = new BookCriteria();
		if (!categoryName.equals(FILTER_ALL_CATEGORY)) {
			criteria.setCategory(BookCategoryStub.valueOf(categoryName));
		}
		this.forward(request, response, criteria, categoryName);
	}

	private void forward(final HttpServletRequest request, final HttpServletResponse response, BookCriteria criteria, String categoryValue)
			throws ServletException, IOException {
		try {
			final List<BookStub> books = this.facade.getBooks(criteria);
			request.setAttribute(ATTR_BOOKS, books);
		} catch (final FacadeException e) {
			LOGGER.error(e, e);
		}
		request.setAttribute(ATTR_CATEGORY, categoryValue);
		final RequestDispatcher view = request.getRequestDispatcher(Page.LIST.getJspName());
		view.forward(request, response);
	}

}

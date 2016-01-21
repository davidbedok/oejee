package hu.qwaevisz.bookstore.weblayer.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.qwaevisz.bookstore.ejbservice.domain.BookCategoryStub;
import hu.qwaevisz.bookstore.ejbservice.domain.BookStub;
import hu.qwaevisz.bookstore.ejbservice.exception.FacadeException;
import hu.qwaevisz.bookstore.ejbservice.facade.BookFacade;
import hu.qwaevisz.bookstore.weblayer.common.BookAttribute;
import hu.qwaevisz.bookstore.weblayer.common.BookParameter;
import hu.qwaevisz.bookstore.weblayer.common.Page;

@WebServlet("/Book")
public class BookController extends HttpServlet implements BookParameter, BookAttribute {

	private static final long serialVersionUID = -4068275526750462197L;

	private static final Logger LOGGER = Logger.getLogger(BookController.class.getName());

	private static final String TRUE_VALUE = "1";
	private static final String NEW_BOOK_ISBN_FLAG = "-1";

	@EJB
	private BookFacade facade;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String isbn = request.getParameter(ISBN);
		LOGGER.info("Get Book by ISBN (" + isbn + ")");
		if (isbn == null || "".equals(isbn)) {
			response.sendRedirect(Page.LIST.getUrl());
		} else {
			final boolean editFlag = TRUE_VALUE.equals(request.getParameter(EDIT_FLAG));
			BookStub book = null;
			boolean isNew = false;
			if (NEW_BOOK_ISBN_FLAG.equals(isbn)) {
				book = new BookStub("", "", "", BookCategoryStub.SCIFI, 1000, 10);
				isNew = true;
			} else {
				try {
					book = this.facade.getBook(isbn);
				} catch (final FacadeException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(), e);
				}
			}
			this.forward(request, response, editFlag, book, isNew);
		}
	}

	private void forward(final HttpServletRequest request, final HttpServletResponse response, final boolean editFlag, final BookStub book, boolean isNew)
			throws ServletException, IOException {
		request.setAttribute(ATTR_BOOK, book);
		request.setAttribute(ATTR_ISNEW, isNew);
		final RequestDispatcher view = request.getRequestDispatcher(editFlag ? Page.BOOK_EDIT.getJspName() : Page.BOOK_VIEW.getJspName());
		view.forward(request, response);
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String isbn = request.getParameter(ISBN);
		final String author = request.getParameter(AUTHOR);
		final String title = request.getParameter(TITLE);
		final int numberOfPages = Integer.parseInt(request.getParameter(NUMBER_OF_PAGES));
		final double price = Double.parseDouble(request.getParameter(PRICE));
		final BookCategoryStub category = BookCategoryStub.valueOf(request.getParameter(CATEGORY));
		if (isbn == null || "".equals(isbn)) {
			final BookStub book = new BookStub(isbn, author, title, category, price, numberOfPages);
			this.forward(request, response, true, book, true);
		} else {
			BookStub book = null;
			try {
				book = this.facade.saveBook(isbn, author, title, numberOfPages, price, category);
			} catch (final FacadeException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
			this.forward(request, response, false, book, false);
		}
	}

}

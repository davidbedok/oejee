package hu.qwaevisz.bookstore.weblayer.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.qwaevisz.bookstore.ejbservice.exception.FacadeException;
import hu.qwaevisz.bookstore.ejbservice.facade.BookFacade;
import hu.qwaevisz.bookstore.weblayer.common.BookParameter;
import hu.qwaevisz.bookstore.weblayer.common.Page;

@WebServlet("/BookDelete")
public class BookDeleteServlet extends HttpServlet implements BookParameter {

	private static final Logger LOGGER = Logger.getLogger(BookDeleteServlet.class.getName());

	@EJB
	private BookFacade facade;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String isbn = request.getParameter(ISBN);
		LOGGER.info("Delete Book by ISBN (" + isbn + ")");
		try {
			this.facade.removeBook(isbn);
		} catch (final FacadeException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		response.sendRedirect(Page.LIST.getUrl());
	}

}

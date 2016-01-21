package hu.qwaevisz.bookstore.weblayer.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import hu.qwaevisz.bookstore.ejbservice.domain.BookStub;
import hu.qwaevisz.bookstore.ejbservice.exception.FacadeException;
import hu.qwaevisz.bookstore.ejbservice.facade.BookFacade;

@WebServlet("/Book")
public class BookView extends HttpServlet {

	private static final long serialVersionUID = -4068275526750462197L;
	private static final String PARAM_ISBN = "isbn";
	private static final String ATTRIBUTE_BOOK = "book";
	private static final String PAGE = "book.jsp";

	private static final Logger LOGGER = Logger.getLogger(BookView.class);

	@EJB
	private BookFacade facade;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter(PARAM_ISBN);
		LOGGER.info("Get Book by ISBN ("+isbn+")");
		try {
			BookStub book = this.facade.getBook(isbn);
			request.setAttribute(ATTRIBUTE_BOOK, book);
		} catch (FacadeException e) {
			LOGGER.error(e, e);
		}
	    RequestDispatcher view = request.getRequestDispatcher(PAGE);
	    view.forward(request, response); 
	}

}

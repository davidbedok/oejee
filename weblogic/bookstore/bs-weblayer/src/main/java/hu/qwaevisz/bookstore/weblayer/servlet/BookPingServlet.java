package hu.qwaevisz.bookstore.weblayer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.qwaevisz.bookstore.ejbservice.domain.BookStub;
import hu.qwaevisz.bookstore.ejbservice.exception.FacadeException;
import hu.qwaevisz.bookstore.ejbservice.facade.BookFacade;

@WebServlet("/BookPing")
public class BookPingServlet extends HttpServlet {

	private static final long serialVersionUID = -7058255202709402208L;

	private static final Logger LOGGER = Logger.getLogger(BookPingServlet.class.getName());

	@EJB
	private BookFacade facade;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Get Book by user");
		response.setCharacterEncoding("UTF-8");
		final PrintWriter out = response.getWriter();
		try {
			final BookStub book = this.facade.getBook("978-0441172719");
			out.println(book.toString());
		} catch (final FacadeException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			out.println(e.getLocalizedMessage());
		}
		out.close();
	}

}

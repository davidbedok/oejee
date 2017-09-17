package hu.qwaevisz.bookstore.weblayer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import hu.qwaevisz.bookstore.ejbservice.domain.BookStub;
import hu.qwaevisz.bookstore.ejbservice.exception.FacadeException;
import hu.qwaevisz.bookstore.ejbservice.facade.BookFacade;

@WebServlet("/BookPing")
public class BookPingServlet extends HttpServlet {

	private static final long serialVersionUID = -7058255202709402208L;

	private static final Logger LOGGER = Logger.getLogger(BookPingServlet.class);

	@EJB
	private BookFacade facade;

	@Override
	public void init(final ServletConfig config) throws ServletException {
		final ServletContext context = config.getServletContext();
		final String serverInfo = context.getServerInfo();
		LOGGER.info("Context" + context.getContextPath());
	}

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Get Book by user (servletPath: " + request.getServletPath() + ", contextPath: " + request.getContextPath() + ", server: "
				+ request.getServerName() + ":" + request.getServerPort() + " )");
		response.setCharacterEncoding("UTF-8");
		final PrintWriter out = response.getWriter();
		try {
			final BookStub book = this.facade.getBook("978-0441172719");
			out.println(book.toString());
		} catch (final FacadeException e) {
			LOGGER.error(e, e);
			out.println(e.getLocalizedMessage());
		}
		out.close();
	}

}

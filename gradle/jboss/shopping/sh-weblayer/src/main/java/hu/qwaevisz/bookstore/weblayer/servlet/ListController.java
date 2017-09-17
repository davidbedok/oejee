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

import hu.qwaevisz.shopping.report.domain.Transaction;
import hu.qwaevisz.shopping.report.exception.ReportException;
import hu.qwaevisz.shopping.report.service.ShoppingReport;

@WebServlet("/List")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = 7309160521583810479L;

	private static final Logger LOGGER = Logger.getLogger(ListController.class);

	@EJB
	private ShoppingReport service;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		try {
			final List<Transaction> transactions = this.service.getTransactions();
			request.setAttribute("transactions", transactions);
		} catch (final ReportException e) {
			LOGGER.error(e, e);
		}
		final RequestDispatcher view = request.getRequestDispatcher("list.jsp");
		view.forward(request, response);
	}

}

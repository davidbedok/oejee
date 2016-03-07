package hu.qwaevisz.bookstore.weblayer.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import hu.qwaevisz.shopping.report.exception.ReportException;
import hu.qwaevisz.shopping.report.service.ShoppingReport;

@WebServlet("/Report")
public class ReportServlet extends HttpServlet {

	private static final long serialVersionUID = -4726599395465850378L;

	private static final Logger LOGGER = Logger.getLogger(ListController.class);

	@EJB
	private ShoppingReport report;

	@Override
	public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		byte[] data = null;
		final long id = Long.valueOf(request.getParameter("id"));
		try {
			data = this.report.getTransactionDetailsReport(id);
		} catch (final ReportException e) {
			LOGGER.error(e, e);
		}

		response.setHeader("Content-disposition", "inline; filename=\"transaction-details.pdf\"");
		response.setContentType("application/pdf");
		response.getOutputStream().write(data);
	}

}

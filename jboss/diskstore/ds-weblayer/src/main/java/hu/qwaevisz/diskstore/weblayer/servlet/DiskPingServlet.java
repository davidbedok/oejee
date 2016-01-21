package hu.qwaevisz.diskstore.weblayer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import hu.qwaevisz.diskstore.ejbservice.facade.DiskFacade;
import hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskStub;
import hu.qwaevisz.diskstore.ejbserviceclient.exception.ServiceException;

@WebServlet("/DiskPing")
public class DiskPingServlet extends HttpServlet {

	private static final long serialVersionUID = -7058255202709402208L;

	private static final Logger LOGGER = Logger.getLogger(DiskPingServlet.class);

	@EJB
	private DiskFacade facade;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Get Disk (local)");
		response.setCharacterEncoding("UTF-8");
		final PrintWriter out = response.getWriter();
		try {
			final DiskStub disk = this.facade.getDisk(1001);
			out.println(disk.toString());
		} catch (final ServiceException e) {
			LOGGER.error(e, e);
			out.println(e.getLocalizedMessage());
		}
		out.close();
	}

}

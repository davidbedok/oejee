package hu.qwaevisz.diskstore.weblayer.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import hu.qwaevisz.diskstore.ejbservice.facade.DiskFacade;
import hu.qwaevisz.diskstore.ejbserviceclient.exception.ServiceException;
import hu.qwaevisz.diskstore.weblayer.common.DiskParameter;
import hu.qwaevisz.diskstore.weblayer.common.Page;

@WebServlet("/DiskDelete")
public class DiskDeleteServlet extends HttpServlet implements DiskParameter {

	private static final long serialVersionUID = 3115047172240434441L;

	private static final Logger LOGGER = Logger.getLogger(DiskDeleteServlet.class);

	@EJB
	private DiskFacade facade;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String reference = request.getParameter(REFERENCE);
		LOGGER.info("Delete Disk by reference (" + reference + ")");
		try {
			this.facade.removeDisk(reference);
		} catch (final ServiceException e) {
			LOGGER.error(e, e);
		}
		response.sendRedirect(Page.LIST.getUrl());
	}

}

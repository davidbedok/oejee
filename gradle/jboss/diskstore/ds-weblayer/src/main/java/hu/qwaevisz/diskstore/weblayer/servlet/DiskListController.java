package hu.qwaevisz.diskstore.weblayer.servlet;

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

import hu.qwaevisz.diskstore.ejbservice.facade.DiskFacade;
import hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskStub;
import hu.qwaevisz.diskstore.ejbserviceclient.exception.ServiceException;
import hu.qwaevisz.diskstore.weblayer.common.ListAttribute;
import hu.qwaevisz.diskstore.weblayer.common.Page;

@WebServlet("/DiskList")
public class DiskListController extends HttpServlet implements ListAttribute {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(DiskListController.class);

	@EJB
	private DiskFacade facade;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Get All Books");
		try {
			final List<DiskStub> disks = this.facade.getDisks();
			request.setAttribute(ATTR_DISKS, disks);
		} catch (final ServiceException e) {
			LOGGER.error(e, e);
		}
		final RequestDispatcher view = request.getRequestDispatcher(Page.LIST.getJspName());
		view.forward(request, response);
	}

}

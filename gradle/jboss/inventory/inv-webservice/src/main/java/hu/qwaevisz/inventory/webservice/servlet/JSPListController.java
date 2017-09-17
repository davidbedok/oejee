package hu.qwaevisz.inventory.webservice.servlet;

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

import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;
import hu.qwaevisz.inventory.ejbservice.facade.InventoryFacade;
import hu.qwaevisz.inventory.persistence.domain.InventoryItem;
import hu.qwaevisz.inventory.persistence.domain.InventoryType;
import hu.qwaevisz.inventory.webservice.common.ListAttribute;
import hu.qwaevisz.inventory.webservice.common.Page;

@WebServlet("/JSPList")
public class JSPListController extends HttpServlet implements ListAttribute {

	private static final long serialVersionUID = 1190531898097507410L;

	private static final Logger LOGGER = Logger.getLogger(JSPListController.class);

	@EJB
	private InventoryFacade facade;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Get All Inventories by type");
		try {
			final List<InventoryItem> inventories = this.facade.getInventories(InventoryType.BOOK);
			request.setAttribute(ATTR_INVENTORIES, inventories);
		} catch (final AdaptorException e) {
			LOGGER.error(e, e);
		}
		final RequestDispatcher view = request.getRequestDispatcher(Page.JAVA_SERVER_PAGES.getServerPage());
		view.forward(request, response);
	}

}

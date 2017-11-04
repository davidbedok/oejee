package hu.qwaevisz.diskstore.weblayer.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import hu.qwaevisz.diskstore.ejbservice.facade.DiskFacade;
import hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskCategoryStub;
import hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskStub;
import hu.qwaevisz.diskstore.ejbserviceclient.exception.ServiceException;
import hu.qwaevisz.diskstore.weblayer.common.DiskAttribute;
import hu.qwaevisz.diskstore.weblayer.common.DiskParameter;
import hu.qwaevisz.diskstore.weblayer.common.Page;

@WebServlet("/Disk")
public class DiskController extends HttpServlet implements DiskParameter, DiskAttribute {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(DiskController.class);

	private static final String TRUE_VALUE = "1";
	private static final String NEW_DISK_REFERENCE_FLAG = "-1";

	@EJB
	private DiskFacade facade;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String reference = request.getParameter(REFERENCE);
		LOGGER.info("Get Disk by reference (" + reference + ")");
		if (reference == null || "".equals(reference)) {
			response.sendRedirect(Page.LIST.getUrl());
		} else {
			final boolean editFlag = TRUE_VALUE.equals(request.getParameter(EDIT_FLAG));
			DiskStub disk = null;
			boolean isNew = false;
			if (NEW_DISK_REFERENCE_FLAG.equals(reference)) {
				disk = new DiskStub("", "", "", DiskCategoryStub.OTHER, 0, 0);
				isNew = true;
			} else {
				try {
					disk = this.facade.getDisk(reference);
				} catch (final ServiceException e) {
					LOGGER.error(e, e);
				}
			}
			this.forward(request, response, editFlag, disk, isNew);
		}
	}

	private void forward(final HttpServletRequest request, final HttpServletResponse response, final boolean editFlag, final DiskStub disk, final boolean isNew)
			throws ServletException, IOException {
		request.setAttribute(ATTR_DISK, disk);
		request.setAttribute(ATTR_ISNEW, isNew);
		final RequestDispatcher view = request.getRequestDispatcher(editFlag ? Page.DISK_EDIT.getJspName() : Page.DISK_VIEW.getJspName());
		view.forward(request, response);
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String reference = request.getParameter(REFERENCE);
		final String author = request.getParameter(AUTHOR);
		final String title = request.getParameter(TITLE);
		final int numberOfSongs = Integer.parseInt(request.getParameter(NUMBER_OF_SONGS));
		final double price = Double.parseDouble(request.getParameter(PRICE));
		final DiskCategoryStub category = DiskCategoryStub.valueOf(request.getParameter(CATEGORY));
		if (reference == null || "".equals(reference)) {
			final DiskStub disk = new DiskStub(reference, author, title, category, price, numberOfSongs);
			this.forward(request, response, true, disk, true);
		} else {
			DiskStub disk = null;
			try {
				disk = this.facade.saveDisk(reference, author, title, category, price, numberOfSongs);
			} catch (final ServiceException e) {
				LOGGER.error(e, e);
			}
			this.forward(request, response, false, disk, false);
		}
	}

}

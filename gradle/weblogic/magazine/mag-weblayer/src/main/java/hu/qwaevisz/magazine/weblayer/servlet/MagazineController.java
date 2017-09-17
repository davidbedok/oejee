package hu.qwaevisz.magazine.weblayer.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.qwaevisz.magazine.ejbservice.domain.MagazineCategoryStub;
import hu.qwaevisz.magazine.ejbservice.domain.MagazineStub;
import hu.qwaevisz.magazine.ejbservice.exception.FacadeException;
import hu.qwaevisz.magazine.ejbservice.facade.MagazineFacade;
import hu.qwaevisz.magazine.weblayer.common.MagazineAttribute;
import hu.qwaevisz.magazine.weblayer.common.MagazineParameter;
import hu.qwaevisz.magazine.weblayer.common.Page;

@WebServlet("/Magazine")
public class MagazineController extends HttpServlet implements MagazineParameter, MagazineAttribute {

	private static final long serialVersionUID = -4068275526750462197L;

	private static final Logger LOGGER = Logger.getLogger(MagazineController.class.getName());

	private static final String TRUE_VALUE = "1";
	private static final String NEW_MAGAZINE_REFERENCE_FLAG = "-1";

	@EJB
	private MagazineFacade facade;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String reference = request.getParameter(REFERENCE);
		LOGGER.info("Get Magazine by reference (" + reference + ")");
		if (reference == null || "".equals(reference)) {
			response.sendRedirect(Page.LIST.getUrl());
		} else {
			final boolean editFlag = TRUE_VALUE.equals(request.getParameter(EDIT_FLAG));
			MagazineStub magazine = null;
			boolean isNew = false;
			if (NEW_MAGAZINE_REFERENCE_FLAG.equals(reference)) {
				magazine = new MagazineStub("", "", "", MagazineCategoryStub.GAMES, 1000, 10);
				isNew = true;
			} else {
				try {
					magazine = this.facade.getReference(reference);
				} catch (final FacadeException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(), e);
				}
			}
			this.forward(request, response, editFlag, magazine, isNew);
		}
	}

	private void forward(final HttpServletRequest request, final HttpServletResponse response, final boolean editFlag, final MagazineStub book,
			final boolean isNew) throws ServletException, IOException {
		request.setAttribute(ATTR_MAGAZINE, book);
		request.setAttribute(ATTR_ISNEW, isNew);
		final RequestDispatcher view = request.getRequestDispatcher(editFlag ? Page.MAGAZINE_EDIT.getJspName() : Page.MAGAZINE_VIEW.getJspName());
		view.forward(request, response);
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String reference = request.getParameter(REFERENCE);
		final String publisher = request.getParameter(PUBLISHER);
		final String title = request.getParameter(TITLE);
		final int numberOfPages = Integer.parseInt(request.getParameter(NUMBER_OF_PAGES));
		final double price = Double.parseDouble(request.getParameter(PRICE));
		final MagazineCategoryStub category = MagazineCategoryStub.valueOf(request.getParameter(CATEGORY));
		if (reference == null || "".equals(reference)) {
			final MagazineStub book = new MagazineStub(reference, publisher, title, category, price, numberOfPages);
			this.forward(request, response, true, book, true);
		} else {
			MagazineStub magazine = null;
			try {
				magazine = this.facade.saveMagazine(reference, publisher, title, numberOfPages, price, category);
			} catch (final FacadeException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
			this.forward(request, response, false, magazine, false);
		}
	}

}

package hu.qwaevisz.magazine.weblayer.servlet;

import java.io.IOException;
import java.util.List;
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
import hu.qwaevisz.magazine.ejbservice.domain.MagazineCriteria;
import hu.qwaevisz.magazine.ejbservice.domain.MagazineStub;
import hu.qwaevisz.magazine.ejbservice.exception.FacadeException;
import hu.qwaevisz.magazine.ejbservice.facade.MagazineFacade;
import hu.qwaevisz.magazine.weblayer.common.FormValue;
import hu.qwaevisz.magazine.weblayer.common.ListAttribute;
import hu.qwaevisz.magazine.weblayer.common.ListParameter;
import hu.qwaevisz.magazine.weblayer.common.Page;

@WebServlet("/MagazineList")
public class MagazineListController extends HttpServlet implements ListAttribute, ListParameter, FormValue {

	private static final long serialVersionUID = -1977646750178615187L;

	private static final Logger LOGGER = Logger.getLogger(MagazineListController.class.getName());

	@EJB
	private MagazineFacade facade;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Get All Magazines");
		try {
			final List<MagazineStub> magazines = this.facade.getReferences(new MagazineCriteria());
			request.setAttribute(ATTR_MAGAZINES, magazines);
		} catch (final FacadeException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		this.forward(request, response, new MagazineCriteria(), FILTER_ALL_CATEGORY);
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String categoryName = request.getParameter(CATEGORY);
		final MagazineCriteria criteria = new MagazineCriteria();
		if (!categoryName.equals(FILTER_ALL_CATEGORY)) {
			criteria.setCategory(MagazineCategoryStub.valueOf(categoryName));
		}
		this.forward(request, response, criteria, categoryName);
	}

	private void forward(final HttpServletRequest request, final HttpServletResponse response, final MagazineCriteria criteria, final String categoryValue)
			throws ServletException, IOException {
		try {
			final List<MagazineStub> magazines = this.facade.getReferences(criteria);
			request.setAttribute(ATTR_MAGAZINES, magazines);
		} catch (final FacadeException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		request.setAttribute(ATTR_CATEGORY, categoryValue);
		final RequestDispatcher view = request.getRequestDispatcher(Page.LIST.getJspName());
		view.forward(request, response);
	}

}

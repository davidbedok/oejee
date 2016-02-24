package hu.qwaevisz.magazine.weblayer.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import hu.qwaevisz.magazine.ejbservice.exception.FacadeException;
import hu.qwaevisz.magazine.ejbservice.facade.MagazineFacade;
import hu.qwaevisz.magazine.weblayer.common.MagazineParameter;
import hu.qwaevisz.magazine.weblayer.common.Page;

@WebServlet("/MagazineDelete")
public class MagazineDeleteServlet extends HttpServlet implements MagazineParameter {

	private static final long serialVersionUID = -7688739575153938635L;

	private static final Logger LOGGER = Logger.getLogger(MagazineDeleteServlet.class);

	@EJB
	private MagazineFacade facade;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String reference = request.getParameter(REFERENCE);
		LOGGER.info("Delete Magazine by reference (" + reference + ")");
		try {
			this.facade.removeMagazine(reference);
		} catch (final FacadeException e) {
			LOGGER.error(e, e);
		}
		response.sendRedirect(Page.LIST.getUrl());
	}

}

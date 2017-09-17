package hu.qwaevisz.magazine.weblayer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.qwaevisz.magazine.ejbservice.domain.MagazineStub;
import hu.qwaevisz.magazine.ejbservice.exception.FacadeException;
import hu.qwaevisz.magazine.ejbservice.facade.MagazineFacade;

@WebServlet("/MagazinePing")
public class MagazinePingServlet extends HttpServlet {

	private static final long serialVersionUID = -7058255202709402208L;

	private static final Logger LOGGER = Logger.getLogger(MagazinePingServlet.class.getName());

	@EJB
	private MagazineFacade facade;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Get Magazine by user");
		response.setCharacterEncoding("UTF-8");
		final PrintWriter out = response.getWriter();
		try {
			final MagazineStub magazine = this.facade.getReference("ISSN2062-5200");
			out.println(magazine.toString());
		} catch (final FacadeException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			out.println(e.getLocalizedMessage());
		}
		out.close();
	}

}

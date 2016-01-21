package hu.qwaevisz.webstore.weblayer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import hu.qwaevisz.webstore.ejbservice.domain.Basket;
import hu.qwaevisz.webstore.ejbservice.exception.ServiceException;
import hu.qwaevisz.webstore.ejbservice.service.WebBasketService;

@WebServlet("/BasketPing")
public class BasketPingServlet extends HttpServlet {

	private static final long serialVersionUID = -7058255202709402208L;

	private static final Logger LOGGER = Logger.getLogger(BasketPingServlet.class);

	@EJB
	private WebBasketService facade;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Get User's Basket");
		response.setCharacterEncoding("UTF-8");
		final PrintWriter out = response.getWriter();
		try {
			final Basket basket = this.facade.getContent();
			out.println(basket);
		} catch (final ServiceException e) {
			LOGGER.error(e, e);
			out.println(e.getLocalizedMessage());
		}
		out.close();
	}

}

package hu.qwaevisz.magazine.weblayer.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.qwaevisz.magazine.weblayer.common.LoginAttribute;
import hu.qwaevisz.magazine.weblayer.common.Page;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet implements LoginAttribute {

	private static final long serialVersionUID = 1599166100486735562L;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ATTR_USERNAME, "");
		request.setAttribute(ATTR_ERROR, "");
		final RequestDispatcher view = request.getRequestDispatcher(Page.LOGIN.getJspName());
		view.forward(request, response);
	}

}

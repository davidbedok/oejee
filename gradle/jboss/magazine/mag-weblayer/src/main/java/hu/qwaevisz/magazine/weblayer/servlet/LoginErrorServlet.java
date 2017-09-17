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

@WebServlet("/LoginError")
public class LoginErrorServlet extends HttpServlet implements LoginAttribute {

	private static final long serialVersionUID = 1599166100486735562L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String userName = request.getParameter("j_username");

		request.setAttribute(ATTR_USERNAME, userName);
		request.setAttribute(ATTR_ERROR, "Login failed");

		final RequestDispatcher view = request.getRequestDispatcher(Page.LOGIN.getJspName());
		view.forward(request, response);
	}

}

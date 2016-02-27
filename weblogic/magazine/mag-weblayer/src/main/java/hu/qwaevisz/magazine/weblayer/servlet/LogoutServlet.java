package hu.qwaevisz.magazine.weblayer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.qwaevisz.magazine.weblayer.common.Page;

@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1599166100486735562L;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", new java.util.Date().toString());
		if (request.getSession(false) != null) {
			request.getSession(false).invalidate();
		}
		if (request.getSession() != null) {
			request.getSession().invalidate();
		}
		// request.logout();
		response.sendRedirect(Page.LIST.getUrl());
	}

}

package hu.qwaevisz.school.weblayer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import hu.qwaevisz.school.ejbservice.domain.StudentStub;
import hu.qwaevisz.school.ejbservice.exception.AdaptorException;
import hu.qwaevisz.school.ejbservice.facade.StudentFacade;

@WebServlet("/StudentPing")
public class StudentPingServlet extends HttpServlet {

	private static final long serialVersionUID = -7058255202709402208L;

	private static final Logger LOGGER = Logger.getLogger(StudentPingServlet.class);

	@EJB
	private StudentFacade facade;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Get Student by user");
		response.setCharacterEncoding("UTF-8");
		final PrintWriter out = response.getWriter();
		try {
			final StudentStub student = this.facade.getStudent("WI53085");
			out.println(student.toString());
		} catch (final AdaptorException e) {
			LOGGER.error(e, e);
			out.println(e.getLocalizedMessage());
		}
		out.close();
	}

}

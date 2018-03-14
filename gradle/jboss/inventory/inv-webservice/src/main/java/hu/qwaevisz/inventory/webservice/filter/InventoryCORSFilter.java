package hu.qwaevisz.inventory.webservice.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebFilter(filterName = "InventoryCrossOriginResourceSharingFilter", urlPatterns = { "/*" })
public class InventoryCORSFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(InventoryCORSFilter.class);

	public static final String ALLOW_ORIGIN = "Access-Control-Allow-Origin";
	public static final String ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
	public static final String ALLOW_METHODS = "Access-Control-Allow-Methods";
	public static final String ALLOW_HEADERS = "Access-Control-Allow-Headers";
	public static final String MAX_AGE = "Access-Control-Max-Age";

	@Override
	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain chain)
			throws IOException, ServletException {
		final String contentType = servletRequest.getContentType();
		if (servletRequest instanceof HttpServletRequest) {
			final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
			final String method = httpServletRequest.getMethod();
			final String url = httpServletRequest.getRequestURL().toString();
			final String queryString = ((HttpServletRequest) servletRequest).getQueryString();
			if (LOGGER.isTraceEnabled()) {
				LOGGER.trace("Request " + method + " url: '" + url + "', query: '" + queryString + "', contentType: " + contentType + "");
			}
		} else {
			if (LOGGER.isTraceEnabled()) {
				LOGGER.trace("This is not HttpServletRequest (contentType: " + contentType + ").");
			}
		}

		final HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setHeader(ALLOW_ORIGIN, "*");
		response.setHeader(ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		response.setHeader(MAX_AGE, "1209600");
		response.setHeader(ALLOW_HEADERS, "x-requested-with, origin, content-type, accept, X-Codingpedia, authorization");
		response.setHeader(ALLOW_CREDENTIALS, "true");

		response.setHeader("Cache-Control", "no-cache");
		chain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(final FilterConfig filterConfig) {
	}

	@Override
	public void destroy() {
	}

}

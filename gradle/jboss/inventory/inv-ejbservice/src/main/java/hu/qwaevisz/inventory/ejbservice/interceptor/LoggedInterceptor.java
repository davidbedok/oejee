package hu.qwaevisz.inventory.ejbservice.interceptor;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

@Logged
@Interceptor
public class LoggedInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(LoggedInterceptor.class);

	@AroundInvoke
	public Object logMethodInvocations(InvocationContext context) throws Exception {
		final StringBuilder info = new StringBuilder();
		info.append(context.getTarget().getClass().getName()).append(".").append(context.getMethod().getName()).append("(");
		final Object[] parameters = context.getParameters();
		if (parameters != null) {
			int i = parameters.length - 1;
			for (final Object parameter : parameters) {
				info.append(parameter.toString());
				if (i > 0) {
					info.append(",");
				}
				i--;
			}
		}
		info.append(")");
		LOGGER.info("Entering: " + info.toString());
		final long start = System.currentTimeMillis();
		final Object result = context.proceed();
		final long end = System.currentTimeMillis();
		LOGGER.info("Exiting: " + info.toString() + " - running time: " + (end - start) + " millisecond(s)");
		return result;
	}

}

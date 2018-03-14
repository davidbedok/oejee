package hu.qwaevisz.inventory.ejbservice.interceptor;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

import hu.qwaevisz.inventory.ejbservice.domain.InventoryItemBean;

public class CopyrightInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(CopyrightInterceptor.class);

	@AroundInvoke
	public Object decorateCoinWithCopyright(InvocationContext context) throws Exception {
		LOGGER.info("Copyright Interceptor activated in " + context.getMethod().getName());
		final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		final Object result = context.proceed();
		if (result instanceof InventoryItemBean) {
			this.modifyInventoryItemName(result, currentYear);
		} else if (result instanceof List) {
			final List<?> resultList = (List<?>) result;
			for (final Object resultItem : resultList) {
				if (resultItem instanceof InventoryItemBean) {
					this.modifyInventoryItemName(resultItem, currentYear);
				}
			}
		}
		return result;
	}

	private void modifyInventoryItemName(Object object, int currentYear) {
		final InventoryItemBean item = (InventoryItemBean) object;
		final String description = item.getName();
		item.setName(description + " © " + currentYear + " Inventory");
		LOGGER.info("Add copyright for " + item.getReference() + "'s name.");
	}

}

package hu.qwaevisz.inventory.webservice;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import hu.qwaevisz.inventory.ejbservice.domain.InventoryItemBean;
import hu.qwaevisz.inventory.ejbservice.domain.PricingStrategy;
import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;
import hu.qwaevisz.inventory.ejbservice.facade.InventoryFacade;

@Stateless
public class PurchaseRestServiceBean implements PurchaseRestService {

	private static final Logger LOGGER = Logger.getLogger(PurchaseRestServiceBean.class);

	@EJB
	private InventoryFacade facade;

	@Override
	public InventoryItemBean buyInventoryItem(String reference) throws AdaptorException {
		LOGGER.info("Buy inventory item (" + reference + ")");
		return this.facade.buyInventoryItem(reference);
	}

	@Override
	public InventoryItemBean buyInventoryItem(String reference, PricingStrategy pricing) throws AdaptorException {
		LOGGER.info("Buy inventory item  (" + reference + ")");
		return this.facade.buyInventoryItem(reference, pricing);
	}

	@Override
	public Response optionsAll(String path) {
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}

package hu.qwaevisz.inventory.webservice;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import hu.qwaevisz.inventory.ejbservice.domain.InternationalInventoryItemBean;
import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;
import hu.qwaevisz.inventory.ejbservice.facade.InventoryFacade;

@Stateless
public class InternationalRestServiceBean implements InternationalRestService {

	private static final Logger LOGGER = Logger.getLogger(InternationalRestServiceBean.class);

	@EJB
	private InventoryFacade facade;

	@Override
	public InternationalInventoryItemBean getInventoryStub(String reference) throws AdaptorException {
		LOGGER.info("Get Inventory by " + reference + " (reference)");
		return this.facade.getInternationalInventoryItem(reference);
	}

	@Override
	public InternationalInventoryItemBean getInventoryStubWithEvents(String reference) throws AdaptorException {
		LOGGER.info("Get Inventory (with events) by " + reference + " (reference)");
		return this.facade.getInternationalInventoryItemWithEvent(reference);
	}

	@Override
	public Response optionsAll(String path) {
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}

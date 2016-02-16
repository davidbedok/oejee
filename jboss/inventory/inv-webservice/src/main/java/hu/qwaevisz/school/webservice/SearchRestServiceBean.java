package hu.qwaevisz.school.webservice;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;
import hu.qwaevisz.inventory.ejbservice.facade.InventoryFacade;
import hu.qwaevisz.inventory.persistence.domain.Inventory;
import hu.qwaevisz.inventory.persistence.domain.InventoryType;

@Stateless
public class SearchRestServiceBean implements SearchRestService {

	private static final Logger LOGGER = Logger.getLogger(SearchRestServiceBean.class);

	@EJB
	private InventoryFacade facade;

	@Override
	public Inventory getInventory(String reference) throws AdaptorException {
		LOGGER.info("Get Inventory by " + reference + " (reference)");
		return this.facade.getInventory(reference);
	}

	@Override
	public List<Inventory> getInventories(InventoryType type) throws AdaptorException {
		LOGGER.info("Get Inventories by " + type + " type");
		return this.facade.getInventories(type);
	}

	@Override
	public List<Inventory> getInventories(InventoryType type, String nameTerm) throws AdaptorException {
		LOGGER.info("Get Inventories by " + type + " type and " + nameTerm + " name");
		return this.facade.getInventories(type, nameTerm);
	}

	@Override
	public Response optionsAll(String path) {
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}

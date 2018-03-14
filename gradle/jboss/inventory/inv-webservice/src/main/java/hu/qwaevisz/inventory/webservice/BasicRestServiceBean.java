package hu.qwaevisz.inventory.webservice;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import hu.qwaevisz.inventory.ejbservice.domain.InventoryItemBean;
import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;
import hu.qwaevisz.inventory.ejbservice.facade.InventoryFacade;
import hu.qwaevisz.inventory.persistence.domain.InventoryType;

@Stateless
public class BasicRestServiceBean implements BasicRestService {

	private static final Logger LOGGER = Logger.getLogger(BasicRestServiceBean.class);

	@EJB
	private InventoryFacade facade;

	@Override
	public InventoryItemBean getInventory(String reference) throws AdaptorException {
		LOGGER.info("Get inventory item by " + reference + " (reference)");
		return this.facade.getInventoryItem(reference);
	}

	@Override
	public List<InventoryItemBean> getInventoryItems(InventoryType type) throws AdaptorException {
		LOGGER.info("Get inventory items by " + type + " type");
		return this.facade.getInventoryItems(type);
	}

	@Override
	public List<InventoryItemBean> getInventoryItems(InventoryType type, String nameTerm) throws AdaptorException {
		LOGGER.info("Get inventory items by " + type + " type and " + nameTerm + " name term");
		return this.facade.getInventoryItems(type, nameTerm);
	}

	@Override
	public Response optionsAll(String path) {
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}

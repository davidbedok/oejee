package hu.qwaevisz.inventory.webservice;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;
import hu.qwaevisz.inventory.ejbservice.facade.InventoryFacade;

@Stateless
public class ConfigurationRestServiceBean implements ConfigurationRestService {

	private static final Logger LOGGER = Logger.getLogger(ConfigurationRestServiceBean.class);

	@EJB
	private InventoryFacade facade;

	@Override
	public List<Integer> getRandomNumbers(int quantity) throws AdaptorException {
		LOGGER.info("Get " + quantity + " random numbers");
		return this.facade.getRandomNumbers(quantity);
	}

	@Override
	public String getHost() throws AdaptorException {
		LOGGER.info("Get host");
		return this.facade.getHost();
	}

	@Override
	public Response optionsAll(String path) {
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}

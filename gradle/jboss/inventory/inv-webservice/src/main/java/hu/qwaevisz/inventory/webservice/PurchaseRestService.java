package hu.qwaevisz.inventory.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import hu.qwaevisz.inventory.ejbservice.domain.InventoryItemBean;
import hu.qwaevisz.inventory.ejbservice.domain.PricingStrategy;
import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;

@Path("/purchase")
public interface PurchaseRestService {

	@GET
	@Path("/{reference}")
	@Produces(MediaType.APPLICATION_JSON)
	InventoryItemBean buyInventoryItem(@PathParam("reference") String reference) throws AdaptorException;

	@GET
	@Path("/{reference}/{pricing}")
	@Produces(MediaType.APPLICATION_JSON)
	InventoryItemBean buyInventoryItem(@PathParam("reference") String reference, @PathParam("pricing") PricingStrategy pricing) throws AdaptorException;

	@OPTIONS
	@Path("{path:.*}")
	Response optionsAll(@PathParam("path") String path);

}

package hu.qwaevisz.school.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;
import hu.qwaevisz.inventory.persistence.domain.Inventory;
import hu.qwaevisz.inventory.persistence.domain.InventoryType;

@Path("/search")
public interface SearchRestService {

	@GET
	@Path("/{reference}")
	@Produces("application/json")
	Inventory getInventory(@PathParam("reference") String reference) throws AdaptorException;

	@GET
	@Path("/list/{type}")
	@Produces("application/json")
	List<Inventory> getInventories(@PathParam("type") InventoryType type) throws AdaptorException;

	@OPTIONS
	@Path("{path:.*}")
	Response optionsAll(@PathParam("path") String path);

}

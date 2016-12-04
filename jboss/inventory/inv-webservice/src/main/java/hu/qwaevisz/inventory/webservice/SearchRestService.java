package hu.qwaevisz.inventory.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import hu.qwaevisz.inventory.ejbservice.domain.InventoryItemStub;
import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;
import hu.qwaevisz.inventory.persistence.domain.InventoryItem;
import hu.qwaevisz.inventory.persistence.domain.InventoryType;

@Path("/search")
public interface SearchRestService {

	@GET
	@Path("/{reference}")
	@Produces(MediaType.APPLICATION_JSON)
	InventoryItem getInventory(@PathParam("reference") String reference) throws AdaptorException;

	@GET
	@Path("/list/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	List<InventoryItem> getInventories(@PathParam("type") InventoryType type) throws AdaptorException;

	@GET
	@Path("/list/{type}/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	List<InventoryItem> getInventories(@PathParam("type") InventoryType type, @PathParam("name") String nameTerm) throws AdaptorException;

	@GET
	@Path("/host")
	String getHost() throws AdaptorException;

	@GET
	@Path("/stub/{reference}")
	@Produces(MediaType.APPLICATION_JSON)
	InventoryItemStub getInventoryStub(@PathParam("reference") String reference) throws AdaptorException;

	@GET
	@Path("/numbers/{quantity}")
	@Produces(MediaType.APPLICATION_JSON)
	List<Integer> getRandomNumbers(@PathParam("quantity") int quantity) throws AdaptorException;

	@OPTIONS
	@Path("{path:.*}")
	Response optionsAll(@PathParam("path") String path);

}

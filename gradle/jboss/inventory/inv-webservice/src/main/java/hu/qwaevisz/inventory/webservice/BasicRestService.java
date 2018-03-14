package hu.qwaevisz.inventory.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import hu.qwaevisz.inventory.ejbservice.domain.InventoryItemBean;
import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;
import hu.qwaevisz.inventory.persistence.domain.InventoryType;

@Path("/basic")
public interface BasicRestService {

	@GET
	@Path("/{reference}")
	@Produces(MediaType.APPLICATION_JSON)
	InventoryItemBean getInventory(@PathParam("reference") String reference) throws AdaptorException;

	@GET
	@Path("/list/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	List<InventoryItemBean> getInventoryItems(@PathParam("type") InventoryType type) throws AdaptorException;

	@GET
	@Path("/list/{type}/{nameTerm}")
	@Produces(MediaType.APPLICATION_JSON)
	List<InventoryItemBean> getInventoryItems(@PathParam("type") InventoryType type, @PathParam("nameTerm") String nameTerm) throws AdaptorException;

	@OPTIONS
	@Path("{path:.*}")
	Response optionsAll(@PathParam("path") String path);

}

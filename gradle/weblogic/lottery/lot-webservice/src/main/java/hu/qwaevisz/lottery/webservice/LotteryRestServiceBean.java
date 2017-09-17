package hu.qwaevisz.lottery.webservice;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import hu.qwaevisz.lottery.ejbservice.domain.EventStub;
import hu.qwaevisz.lottery.ejbservice.domain.IntegerStub;
import hu.qwaevisz.lottery.ejbservice.exception.AdaptorException;
import hu.qwaevisz.lottery.ejbservice.facade.LotteryFacade;

@Stateless
@Path("/service")
public class LotteryRestServiceBean {

	private static final Logger LOGGER = Logger.getLogger(LotteryRestServiceBean.class.getName());

	@EJB
	private LotteryFacade facade;

	@GET
	@Path("/event/list")
	@Produces("application/json")
	public List<EventStub> getAllEvents() throws AdaptorException {
		LOGGER.info("Get All Events");
		return this.facade.getAllEvents();
	}

	@GET
	@Path("/event/latest")
	@Produces("application/json")
	public EventStub getLatestEvent() throws AdaptorException {
		LOGGER.info("Get the latest Event");
		return this.facade.getLatestEvent();
	}

	@POST
	@Path("/check/{numbers}")
	@Produces("application/json")
	public IntegerStub checkNumbers(@PathParam("numbers") String numbers) throws AdaptorException {
		LOGGER.info("Check numbers (" + numbers + ")");
		String[] items = numbers.split(",");
		int [] nums = new int[items.length];
		int index = 0;
		for ( String item : items ) {
			nums[index++] = Integer.parseInt(item);
		}
		return new IntegerStub(this.facade.checkNumbers(nums));
	}

	@OPTIONS
	@Path("{path:.*}")
	public Response optionsAll(String path) {
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}

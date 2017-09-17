package hu.qwaevisz.lottery.webservice;

import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import hu.qwaevisz.lottery.ejbservice.domain.EventStub;
import hu.qwaevisz.lottery.ejbservice.exception.AdaptorException;
import hu.qwaevisz.lottery.ejbservice.facade.LotteryFacade;

@Stateless
public class LotteryRestServiceBean implements LotteryRestService {

	private static final Logger LOGGER = Logger.getLogger(LotteryRestServiceBean.class);

	@EJB
	private LotteryFacade facade;

	@Override
	public List<EventStub> getAllEvents() throws AdaptorException {
		LOGGER.info("Get All Events");
		return this.facade.getAllEvents();
	}

	@Override
	public EventStub getLatestEvent() throws AdaptorException {
		LOGGER.info("Get the latest Event");
		return this.facade.getLatestEvent();
	}

	@Override
	public int checkNumbers(int[] numbers) throws AdaptorException {
		LOGGER.info("Check numbers (" + Arrays.toString(numbers) + ")");
		return this.facade.checkNumbers(numbers);
	}

	@Override
	public Response optionsAll(String path) {
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}

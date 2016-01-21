package hu.qwaevisz.lottery.ejbservice.facade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hu.qwaevisz.lottery.ejbservice.converter.EventConverter;
import hu.qwaevisz.lottery.ejbservice.domain.EventStub;
import hu.qwaevisz.lottery.ejbservice.exception.AdaptorException;
import hu.qwaevisz.lottery.ejbservice.holder.LotteryStateHolder;
import hu.qwaevisz.lottery.persistence.exception.PersistenceServiceException;
import hu.qwaevisz.lottery.persistence.service.EventService;

@Stateless(mappedName = "ejb/lotteryFacade")
public class LotteryFacadeImpl implements LotteryFacade {

	private static final Logger LOGGER = Logger.getLogger(LotteryFacadeImpl.class);

	@EJB
	private EventService eventService;

	@EJB
	private LotteryStateHolder stateHolder;

	@EJB
	private EventConverter converter;

	@Override
	public List<EventStub> getAllEvents() throws AdaptorException {
		List<EventStub> stubs = new ArrayList<>();
		try {
			stubs = this.converter.to(this.eventService.readAll());
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Get all Events --> " + stubs.size() + " item(s)");
			}
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new AdaptorException(e.getLocalizedMessage());
		}
		return stubs;
	}

	@Override
	public EventStub getLatestEvent() throws AdaptorException {
		EventStub stub = null;
		try {
			stub = this.converter.to(this.eventService.readLatest());
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Get latest Event --> " + stub);
			}
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new AdaptorException(e.getLocalizedMessage());
		}
		return stub;
	}

	@Override
	public void createNewEvent(int[] numbers) throws AdaptorException {
		try {
			this.eventService.create(this.stateHolder.getCurrentPuller(), this.stateHolder.getCurrentPrizePool(), numbers);
		} catch (final PersistenceServiceException e) {
			LOGGER.error(e, e);
			throw new AdaptorException(e.getLocalizedMessage());
		}
	}

	@Override
	public int checkNumbers(int[] numbers) throws AdaptorException {
		final EventStub event = this.getLatestEvent();
		final List<Integer> drawnNumbers = event.getNumbers();
		int numberOfHits = 0;
		for (int i = 0; i < numbers.length; i++) {
			if (drawnNumbers.contains(numbers[i])) {
				LOGGER.debug("Find a HIT! Number: " + numbers[i]);
				numberOfHits++;
			}
		}
		final int distributionPercent = this.stateHolder.getDistribution(numberOfHits);
		LOGGER.debug("Number of hit of " + Arrays.toString(numbers) + ": " + numberOfHits + " (distributionPercent: " + distributionPercent + "%)");
		return Math.round(event.getPrizePool() * distributionPercent / 100);
	}

}

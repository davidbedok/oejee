package hu.qwaevisz.lottery.ejbservice.facade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import hu.qwaevisz.lottery.ejbservice.converter.EventConverter;
import hu.qwaevisz.lottery.ejbservice.domain.EventStub;
import hu.qwaevisz.lottery.ejbservice.exception.AdaptorException;
import hu.qwaevisz.lottery.ejbservice.holder.LotteryStateHolder;
import hu.qwaevisz.lottery.persistence.exception.PersistenceServiceException;
import hu.qwaevisz.lottery.persistence.service.EventService;

@Stateless(mappedName = "ejb/lotteryFacade")
public class LotteryFacadeImpl implements LotteryFacade {

	private static final Logger LOGGER = Logger.getLogger(LotteryFacadeImpl.class.getName());

	@EJB
	private EventService eventService;

	@EJB
	private EventConverter converter;

	@EJB
	private LotteryStateHolder stateHolder;

	@Override
	public List<EventStub> getAllEvents() throws AdaptorException {
		List<EventStub> stubs = new ArrayList<EventStub>();
		try {
			stubs = this.converter.to(this.eventService.readAll());
			if ( LOGGER.isLoggable(Level.FINE)) {
				LOGGER.fine("Get all Events --> " + stubs.size() + " item(s)");
			}
		} catch (final PersistenceServiceException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new AdaptorException(e.getLocalizedMessage());
		}
		return stubs;
	}

	@Override
	public EventStub getLatestEvent() throws AdaptorException {
		EventStub stub = null;
		try {
			stub = this.converter.to(this.eventService.readLatest());
			if ( LOGGER.isLoggable(Level.FINE)) {
				LOGGER.fine("Get latest Event --> " + stub);
			}
		} catch (final PersistenceServiceException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new AdaptorException(e.getLocalizedMessage());
		}
		return stub;
	}

	@Override
	public void createNewEvent(int[] numbers) throws AdaptorException {
		try {
			this.eventService.create("Puller", 100000, numbers);
		} catch (final PersistenceServiceException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new AdaptorException(e.getLocalizedMessage());
		}
	}

	@Override
	public int checkNumbers(int[] numbers) throws AdaptorException {
		LOGGER.info("Check numbers: " + Arrays.toString(numbers));
		final EventStub event = this.getLatestEvent();
		final List<Integer> drawnNumbers = event.getNumbers();
		int numberOfHits = 0;
		for (int i = 0; i < numbers.length; i++) {
			if (drawnNumbers.contains(numbers[i])) {
				LOGGER.fine("Find a HIT! Number: " + numbers[i]);
				numberOfHits++;
			}
		}
		final int distributionPercent = this.stateHolder.getDistribution(numberOfHits);
		LOGGER.info("Number of hit of " + Arrays.toString(numbers) + ": " + numberOfHits + " (distributionPercent: " + distributionPercent + "%)");
		return Math.round(event.getPrizePool() * distributionPercent / 100);
	}

}

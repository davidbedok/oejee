package hu.qwaevisz.lottery.ejbservice.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import hu.qwaevisz.lottery.ejbservice.domain.EventStub;
import hu.qwaevisz.lottery.persistence.entity.DrawnNumber;
import hu.qwaevisz.lottery.persistence.entity.Event;

@Stateless
public class EventConverterImpl implements EventConverter {

	@Override
	public List<EventStub> to(List<Event> events) {
		final List<EventStub> stubs = new ArrayList<>();
		final List<Long> ids = new ArrayList<>();
		for (final Event event : events) {
			final Long currentId = event.getId();
			if (!ids.contains(currentId)) {
				stubs.add(this.to(event));
				ids.add(currentId);
			}
		}
		return stubs;
	}

	@Override
	public EventStub to(final Event event) {
		final EventStub stub = new EventStub(event.getPuller(), event.getPrizePool(), event.getDate());
		for (final DrawnNumber drawnNumber : event.getNumbers()) {
			stub.addNumber(drawnNumber.getValue());
		}
		return stub;
	}

}

package hu.qwaevisz.lottery.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.lottery.ejbservice.domain.EventStub;
import hu.qwaevisz.lottery.persistence.entity.Event;

@Local
public interface EventConverter {

	List<EventStub> to(List<Event> events);

	EventStub to(Event event);

}

package hu.qwaevisz.lottery.persistence.service;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.lottery.persistence.entity.Event;
import hu.qwaevisz.lottery.persistence.exception.PersistenceServiceException;

@Local
public interface EventService {

	void create(String puller, Integer prizePool, int[] numbers) throws PersistenceServiceException;

	Event readLatest() throws PersistenceServiceException;

	List<Event> readAll() throws PersistenceServiceException;

}

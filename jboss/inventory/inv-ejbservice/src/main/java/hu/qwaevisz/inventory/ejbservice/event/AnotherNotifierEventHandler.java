package hu.qwaevisz.inventory.ejbservice.event;

import java.io.Serializable;

import javax.enterprise.event.Observes;

import org.apache.log4j.Logger;

public class AnotherNotifierEventHandler implements Serializable {

	private static final long serialVersionUID = 6897224496179213143L;

	private static final Logger LOGGER = Logger.getLogger(AnotherNotifierEventHandler.class);

	public void handle(@Observes NotifierEvent event) {
		LOGGER.info("New event appears. " + event);
	}

}

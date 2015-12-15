package hu.qwaevisz.inventory.ejbservice.event;

import java.io.Serializable;

import javax.enterprise.event.Observes;

import org.apache.log4j.Logger;

public class NotifierEventHandler implements Serializable {

	private static final long serialVersionUID = -8492686161452234834L;

	private static final Logger LOGGER = Logger.getLogger(NotifierEventHandler.class);

	public void handle(@Observes NotifierEvent event) {
		LOGGER.info(event.client.getName() + " performs an event: " + event.message);
	}

}

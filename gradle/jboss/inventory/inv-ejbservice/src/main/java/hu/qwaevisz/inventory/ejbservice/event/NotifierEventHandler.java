package hu.qwaevisz.inventory.ejbservice.event;

import java.io.Serializable;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import hu.qwaevisz.inventory.ejbservice.client.ClientHolder;
import hu.qwaevisz.inventory.ejbservice.domain.ClientType;
import hu.qwaevisz.inventory.ejbservice.qualifier.ClientFlag;

public class NotifierEventHandler implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(NotifierEventHandler.class);

	@Inject
	@ClientFlag(ClientType.LIVE)
	private ClientHolder clientHolder;

	public void handle(@Observes NotifierEvent event) {
		LOGGER.info("Get " + event.getBean() + " from the Inventory system. Technical user: " + this.clientHolder.getClient());
	}

}

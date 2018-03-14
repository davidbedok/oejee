package hu.qwaevisz.inventory.ejbservice.event;

import java.io.Serializable;

import javax.enterprise.event.Observes;

import org.apache.log4j.Logger;

public class ReportEventHandler implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ReportEventHandler.class);

	public void handle(@Observes NotifierEvent event) {
		LOGGER.info("Report this event: " + event);
	}

}

package hu.qwaevisz.lottery.ejbservice.listener;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.TextMessage;

import hu.qwaevisz.lottery.ejbservice.exception.AdaptorException;
import hu.qwaevisz.lottery.ejbservice.facade.LotteryFacade;

@MessageDriven(name = "LotteryListener", activationConfig = { //
		@ActivationConfigProperty(propertyName = "connectionFactoryJndiName", propertyValue = "jms/demoConnectionFactory"),
		@ActivationConfigProperty(propertyName = "initialContextFactory", propertyValue = "weblogic.jndi.WLInitialContextFactory"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destinationJndiName", propertyValue = "jms/queue/lotteryqueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class LotteryListener implements MessageListener, MessageDrivenBean {

	private static final Logger LOGGER = Logger.getLogger(LotteryListener.class.getName());

	@EJB
	private LotteryFacade facade;

	@PostConstruct
	public void initialize() {
		LOGGER.info("Lottery Listener created...");
	}

	@Override
	public void onMessage(final Message message) {
		try {
			if ( LOGGER.isLoggable(Level.INFO)) {
				final Queue destination = (Queue) message.getJMSDestination();
				final String queueName = destination.getQueueName();
				LOGGER.info("New JMS message arrived into " + queueName + " queue (correlation id: " + message.getJMSCorrelationID() + ")");
			}

			if (message instanceof TextMessage) {
				final TextMessage textMessage = (TextMessage) message;
				String content = textMessage.getText();
				if ( LOGGER.isLoggable(Level.INFO)) {
					LOGGER.info("Received message: " + content);
				}
				content = content.replace("[", "");
				content = content.replace("]", "");
				final String[] numbersStr = content.split(",");
				final int[] numbers = new int[numbersStr.length];
				int i = 0;
				for (final String numberStr : numbersStr) {
					numbers[i++] = Integer.valueOf(numberStr.trim());
				}
				if ( LOGGER.isLoggable(Level.INFO)) {
					LOGGER.info("Parsed content: " + Arrays.toString(numbers));
				}
				this.facade.createNewEvent(numbers);
			} else {
				LOGGER.severe("Received message is not a TextMessage (" + message + ")");
			}
		} catch (final Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	@Override
	public void setMessageDrivenContext(MessageDrivenContext messageDrivenContext) throws EJBException {

	}

	@Override
	public void ejbRemove() throws EJBException {

	}
}

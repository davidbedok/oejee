package hu.qwaevisz.lottery.jmsclient.simulator;

import javax.jms.JMSException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import hu.qwaevisz.lottery.jmsclient.QueueMessageProducer;

public class EndlessSimulator {

	private static final Logger LOGGER = Logger.getLogger(EndlessSimulator.class);

	private final QueueMessageProducer producer;

	public EndlessSimulator(QueueMessageProducer producer) throws NamingException, JMSException {
		this.producer = producer;
		this.producer.openSession();
	}

	public void process(int delay) throws JMSException {
		while (true) {
			final String message = this.producer.createMessage();
			if (this.producer.sendMessage(message)) {
				LOGGER.info(message);
			}
			try {
				Thread.sleep(delay);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

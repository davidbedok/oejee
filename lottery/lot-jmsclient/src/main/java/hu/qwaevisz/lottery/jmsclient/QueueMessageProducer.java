package hu.qwaevisz.lottery.jmsclient;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

public class QueueMessageProducer {

	private static final Logger LOGGER = Logger.getLogger(QueueMessageProducer.class);

	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";

	private final String providerUrl;
	private final String userName;
	private final String password;
	protected final String destinationName;

	private final Context context;

	private Connection connection;
	private Session session;
	private MessageProducer producer;

	public QueueMessageProducer(final String providerUrl, final String userName, final String password, final String destination) throws NamingException {
		this.providerUrl = providerUrl;
		this.userName = userName;
		this.password = password;
		this.destinationName = destination;

		this.context = this.buildContext();
	}

	public void openSession() throws NamingException, JMSException {
		LOGGER.info("Attempting to acquire connection factory \"" + QueueMessageProducer.CONNECTION_FACTORY + "\"");
		final ConnectionFactory connectionFactory = (ConnectionFactory) this.context.lookup(QueueMessageProducer.CONNECTION_FACTORY);
		LOGGER.info("Found connection factory \"" + QueueMessageProducer.CONNECTION_FACTORY + "\" in JNDI");

		LOGGER.info("Attempting to acquire destination \"" + this.destinationName + "\"");
		final Destination destination = (Destination) this.context.lookup(this.destinationName);
		LOGGER.info("Found destination \"" + this.destinationName + "\" in JNDI");

		this.connection = connectionFactory.createConnection(this.userName, this.password);
		this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		this.producer = this.session.createProducer(destination);
		this.connection.start();
	}

	public boolean sendMessage(final String message) {
		boolean result = false;
		try {
			final TextMessage textMessage = this.session.createTextMessage(message);
			this.producer.send(textMessage);
			result = true;
		} catch (final JMSException ex) {
			LOGGER.info("Server not received the message: " + ex.getMessage());
			LOGGER.info("Retrying connection to server.");
			try {
				this.openSession();
				LOGGER.info("Retrying connection to server: successful");
			} catch (NamingException | JMSException e) {
				LOGGER.info("Retrying connection to server: failed");
			}
		} catch (final Exception e) {
			LOGGER.error(e, e);
		}
		return result;
	}

	public void standaloneSendMessage(final String message) throws Exception {
		Connection connection = null;
		try {
			LOGGER.info("Attempting to acquire connection factory \"" + QueueMessageProducer.CONNECTION_FACTORY + "\"");
			final ConnectionFactory connectionFactory = (ConnectionFactory) this.context.lookup(QueueMessageProducer.CONNECTION_FACTORY);
			LOGGER.info("Found connection factory \"" + QueueMessageProducer.CONNECTION_FACTORY + "\" in JNDI");

			LOGGER.info("Attempting to acquire destination \"" + this.destinationName + "\"");
			final Destination destination = (Destination) this.context.lookup(this.destinationName);
			LOGGER.info("Found destination \"" + this.destinationName + "\" in JNDI");

			connection = connectionFactory.createConnection(this.userName, this.password);
			final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			final MessageProducer producer = session.createProducer(destination);
			connection.start();

			final TextMessage textMessage = session.createTextMessage(message);
			producer.send(textMessage);

		} catch (final Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	protected Context buildContext() throws NamingException {
		final Properties environment = new Properties();
		environment.put(Context.INITIAL_CONTEXT_FACTORY, QueueMessageProducer.INITIAL_CONTEXT_FACTORY);
		environment.put(Context.PROVIDER_URL, this.providerUrl);
		environment.put(Context.SECURITY_PRINCIPAL, this.userName);
		environment.put(Context.SECURITY_CREDENTIALS, this.password);
		return new InitialContext(environment);
	}

	@Override
	protected void finalize() throws Throwable {
		if (this.context != null) {
			this.context.close();
		}
	}

	public String createMessage() {
		return "";
	}

}

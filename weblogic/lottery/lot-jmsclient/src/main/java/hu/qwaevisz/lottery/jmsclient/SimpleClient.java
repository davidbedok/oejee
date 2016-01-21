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

public class SimpleClient {

	public static void main(String[] args) throws NamingException, JMSException {
		final Properties environment = new Properties();
		environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		environment.put(Context.PROVIDER_URL, "remote://localhost:4447");
		environment.put(Context.SECURITY_PRINCIPAL, "jmstestuser");
		environment.put(Context.SECURITY_CREDENTIALS, "User#70365");
		Context context = new InitialContext(environment);

		final ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
		final Destination destination = (Destination) context.lookup("jms/queue/lotteryqueue");

		Connection connection = connectionFactory.createConnection("jmstestuser", "User#70365");
		final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		final MessageProducer producer = session.createProducer(destination);
		connection.start();

		final TextMessage textMessage = session.createTextMessage("1, 2, 3, 4, 5");
		producer.send(textMessage);

		connection.close();
	}

}

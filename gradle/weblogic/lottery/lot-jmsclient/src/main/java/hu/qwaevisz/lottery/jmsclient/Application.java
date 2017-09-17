package hu.qwaevisz.lottery.jmsclient;

import java.util.Random;

import hu.qwaevisz.lottery.jmsclient.simulator.EndlessSimulator;

public class Application {

	private static final String PROVIDER_URL = "t3://localhost:7001";
	private static final String USERNAME = "weblogic";
	private static final String PASSWORD = "AlmafA1#";
	private static final String DESTINATION_LOTTERY = "jms/queue/lotteryqueue";

	public static void main(final String[] args) {
		sendSingleMessage();
		// startSimulation(new Random(), 4000);
	}

	public static void sendSingleMessage() {
		try {
			new QueueMessageProducer(Application.PROVIDER_URL, Application.USERNAME, Application.PASSWORD, Application.DESTINATION_LOTTERY)
					.standaloneSendMessage("1, 2, 3, 4, 5");
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static void startSimulation(final Random random, final int delay) {
		try {
			final LotteryProducer producer = new LotteryProducer(Application.PROVIDER_URL, Application.USERNAME, Application.PASSWORD,
					Application.DESTINATION_LOTTERY, random);
			final EndlessSimulator simulator = new EndlessSimulator(producer);
			simulator.process(delay);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}

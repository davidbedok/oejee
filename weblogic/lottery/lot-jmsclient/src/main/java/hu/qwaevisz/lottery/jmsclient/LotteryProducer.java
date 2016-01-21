package hu.qwaevisz.lottery.jmsclient;

import java.util.Random;

import javax.naming.NamingException;

import hu.qwaevisz.lottery.jmsclient.generator.LotteryMessageGenerator;

public class LotteryProducer extends QueueMessageProducer {

	private final LotteryMessageGenerator messageGenerator;

	public LotteryProducer(String providerUrl, String userName, String password, String destination, Random random) throws NamingException {
		super(providerUrl, userName, password, destination);
		this.messageGenerator = new LotteryMessageGenerator(random);
	}

	@Override
	public String createMessage() {
		return this.messageGenerator.generate();
	}

}

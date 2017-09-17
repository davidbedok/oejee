package hu.qwaevisz.lottery.ejbservice.management;

import javax.ejb.EJB;

import org.apache.log4j.Logger;

import hu.qwaevisz.lottery.ejbservice.holder.LotteryStateHolder;

public class LotteryMonitor implements LotteryMonitorMBean {

	private static final Logger LOGGER = Logger.getLogger(LotteryMonitor.class);

	@EJB
	private LotteryStateHolder stateHolder;

	@Override
	public String getPuller() {
		return this.stateHolder.getCurrentPuller();
	}

	@Override
	public void setPuller(String name) {
		LOGGER.info("Set current puller: " + name);
		this.stateHolder.setCurrentPuller(name);
	}

	@Override
	public Integer getPrizePool() {
		return this.stateHolder.getCurrentPrizePool();
	}

	@Override
	public void setPrizePool(Integer value) {
		LOGGER.info("Set Prize pool: " + value);
		this.stateHolder.setCurrentPrizePool(value);
	}

	@Override
	public int getDistribution(int hit) {
		return this.stateHolder.getDistribution(hit);
	}

	@Override
	public int[] getDistributions() {
		final Integer[] values = this.stateHolder.getDistributions().toArray(new Integer[] {});
		final int[] numbers = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			numbers[i] = values[i];
		}
		return numbers;
	}

	@Override
	public void setDistribution(int hit, int value) {
		LOGGER.info("Set distributions (hit: " + hit + ", value: " + value + ")");
		this.stateHolder.setDistribution(hit, value);
	}

	public void start() throws Exception {
		LOGGER.info("Start Lottery MBean");
	}

	public void stop() throws Exception {
		LOGGER.info("Stop Lottery MBean");
	}

}

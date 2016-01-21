package hu.qwaevisz.lottery.ejbservice.management;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.management.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import hu.qwaevisz.lottery.ejbservice.holder.LotteryStateHolder;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class LotteryMonitor extends StandardMBean implements LotteryMonitorMBean, MBeanRegistration {

	private static final Logger LOGGER = Logger.getLogger(LotteryMonitor.class.getName());

	private static final String LOTTERY_STATE_HOLDER_JNDI = "java:global.lottery-1.0.lot-ejbservice.lotteryState";

	public LotteryMonitor() {
		super(LotteryMonitorMBean.class, false);
	}

	private LotteryStateHolder getStateHolder() {
		LotteryStateHolder holder = null;
		try {
			InitialContext context = new InitialContext();
			holder = LotteryStateHolder.class.cast( context.lookup(LOTTERY_STATE_HOLDER_JNDI) );
		} catch (NamingException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return holder;
	}

	@Override
	public String getPuller() {
		LOGGER.info("Get current puller..");
		String puller = "";
		LotteryStateHolder holder = this.getStateHolder();
		if ( holder != null ) {
			puller = holder.getCurrentPuller();
		}
		return puller;
	}

	@Override
	public void setPuller(String name) {
		LOGGER.info("Set current puller: " + name);
		LotteryStateHolder holder = this.getStateHolder();
		if ( holder != null ) {
			holder.setCurrentPuller(name);
		}
	}

	@Override
	public Integer getPrizePool() {
		LOGGER.info("Get prize pool..");
		Integer prizePool = 0;
		LotteryStateHolder holder = this.getStateHolder();
		if ( holder != null ) {
			prizePool = holder.getCurrentPrizePool();
		}
		return prizePool;
	}

	@Override
	public void setPrizePool(Integer value) {
		LOGGER.info("Set Prize pool: " + value);
		LotteryStateHolder holder = this.getStateHolder();
		if ( holder != null ) {
			holder.setCurrentPrizePool(value);
		}
	}

	@Override
	public int getDistribution(int hit) {
		LOGGER.info("Get distribution (hit: "+hit+")..");
		int distribution = 0;
		LotteryStateHolder holder = this.getStateHolder();
		if ( holder != null ) {
			distribution = holder.getDistribution(hit);
		}
		return distribution;
	}

	@Override
	public int[] getDistributions() {
		LOGGER.info("Get distributions..");
		int[] numbers = new int[0];
		LotteryStateHolder holder = this.getStateHolder();
		if ( holder != null ) {
			final Integer[] values = holder.getDistributions().toArray(new Integer[]{});
			numbers = new int[values.length];
			for (int i = 0; i < values.length; i++) {
				numbers[i] = values[i];
			}
		}
		return numbers;
	}

	@Override
	public void setDistribution(int hit, int value) {
		LOGGER.info("Set distributions (hit: " + hit + ", value: " + value + ")");
		LotteryStateHolder holder = this.getStateHolder();
		if ( holder != null ) {
			holder.setDistribution(hit, value);
		}
	}

	public void start() throws Exception {
		LOGGER.info("Start Lottery MBean");
	}

	public void stop() throws Exception {
		LOGGER.info("Stop Lottery MBean");
	}

	@Override
	public ObjectName preRegister(MBeanServer server, ObjectName name) throws Exception {
		return name;
	}

	@Override
	public void postRegister(Boolean registrationDone) {
	}

	@Override
	public void preDeregister() throws Exception {
	}

	@Override
	public void postDeregister() {
	}

}

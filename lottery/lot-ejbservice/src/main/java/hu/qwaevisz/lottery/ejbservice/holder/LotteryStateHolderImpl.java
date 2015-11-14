package hu.qwaevisz.lottery.ejbservice.holder;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import org.apache.log4j.Logger;

@Singleton(mappedName = "ejb/lotteryState")
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class LotteryStateHolderImpl implements LotteryStateHolder {

	private static final Logger LOGGER = Logger.getLogger(LotteryStateHolderImpl.class);

	private String puller;

	private Integer prizePool;

	private PrizeDistibution distribution;

	@PostConstruct
	public void initialize() {
		LOGGER.info("LotteryStateHolder is initialized.");
		this.puller = "Juanita A. Jenkins";
		this.prizePool = 12345;
		this.distribution = new PrizeDistibution();
	}

	@Override
	@Lock(LockType.READ)
	public String getCurrentPuller() {
		return this.puller;
	}

	@Override
	@Lock(LockType.READ)
	public Integer getCurrentPrizePool() {
		return this.prizePool;
	}

	@Override
	@Lock(LockType.WRITE)
	public void setCurrentPrizePool(int value) {
		LOGGER.info("Change Prize pool: " + value);
		this.prizePool = value;
	}

	@Override
	@Lock(LockType.WRITE)
	public void setCurrentPuller(String name) {
		LOGGER.info("Change Puller: " + name);
		this.puller = name;
	}

	@Override
	@Lock(LockType.READ)
	public int getDistribution(int hit) {
		return this.distribution.getDistribution(hit);
	}

	@Override
	@Lock(LockType.READ)
	public List<Integer> getDistributions() {
		return this.distribution.getDistributions();
	}

	@Override
	@Lock(LockType.WRITE)
	public void setDistribution(int hit, int value) {
		this.distribution.setDistribution(hit, value);
	}

}

package hu.qwaevisz.lottery.ejbservice.holder;

import java.util.List;

import javax.ejb.Local;

@Local
public interface LotteryStateHolder {

	String getCurrentPuller();

	void setCurrentPuller(String name);

	Integer getCurrentPrizePool();

	void setCurrentPrizePool(int value);

	int getDistribution(int hit);

	List<Integer> getDistributions();

	void setDistribution(int hit, int value);

}

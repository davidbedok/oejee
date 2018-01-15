package hu.qwaevisz.lottery.ejbservice.management;

@Description("Lottery Monitor")
public interface LotteryMonitorMBean {

	@Description("Get current Puller")
	String getPuller();

	void setPuller(String name);

	@Description("Get current Prize pool")
	Integer getPrizePool();

	void setPrizePool(Integer value);

	@Description("Get the given distribution list item")
	int getDistribution(@PName("hit") int hit);

	@Description("Get the whole distribution list")
	int[] getDistributions();

	@Description("Set the given distribution list item")
	void setDistribution(@PName("hit") int hit, @PName("value") int value);

}

package hu.qwaevisz.lottery.ejbservice.holder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrizeDistibution {

	private static final int[] DEFAULTDISTRIBUTION = new int[] { 1, 3, 6, 10, 80 };

	private final Map<Integer, Integer> distribution;

	public PrizeDistibution() {
		this.distribution = new HashMap<Integer, Integer>();
		for (int i = 1; i <= DEFAULTDISTRIBUTION.length; i++) {
			this.distribution.put(i, DEFAULTDISTRIBUTION[i - 1]);
		}
	}

	public int getDistribution(int hit) {
		int result = 0;
		if (this.isValidHit(hit)) {
			result = this.distribution.get(hit);
		}
		return result;
	}

	private boolean isValidHit(int hit) {
		return hit >= 1 && hit <= DEFAULTDISTRIBUTION.length;
	}

	public List<Integer> getDistributions() {
		return new ArrayList<Integer>(this.distribution.values());
	}

	public void setDistribution(int hit, int value) {
		if (this.isValidHit(hit)) {
			this.distribution.put(hit, value);
		}
	}

}

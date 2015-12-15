package hu.qwaevisz.inventory.ejbservice.cost;

import hu.qwaevisz.inventory.ejbservice.qualifier.Standard;

@Standard
public class NormalCostServiceImpl implements CostService {

	@Override
	public int getCost(int originalValue) {
		return originalValue;
	}

}

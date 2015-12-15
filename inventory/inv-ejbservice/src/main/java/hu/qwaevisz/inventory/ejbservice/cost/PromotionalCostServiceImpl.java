package hu.qwaevisz.inventory.ejbservice.cost;

import hu.qwaevisz.inventory.ejbservice.qualifier.Discount;

@Discount
public class PromotionalCostServiceImpl implements CostService {

	private static final float DISCOUNT_PERCENT = 20;

	@Override
	public int getCost(int originalValue) {
		return Math.round(originalValue * (1 - (DISCOUNT_PERCENT / 100)));
	}

}

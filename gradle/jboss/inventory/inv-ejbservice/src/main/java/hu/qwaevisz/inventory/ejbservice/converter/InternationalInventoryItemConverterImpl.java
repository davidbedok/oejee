package hu.qwaevisz.inventory.ejbservice.converter;

import javax.annotation.Resource;

import hu.qwaevisz.inventory.ejbservice.domain.InternationalInventoryItemBean;
import hu.qwaevisz.inventory.persistence.domain.InventoryItem;

public class InternationalInventoryItemConverterImpl implements InternationalInventoryItemConverter {

	@Resource(lookup = "java:global/currency")
	private String currency;

	@Resource(lookup = "java:global/exchangeRate")
	private int exchangeRate;

	@Override
	public InternationalInventoryItemBean to(InventoryItem item) {
		String label = item.getReference() + "-" + item.getName();
		String price = item.getValue() * this.exchangeRate + " " + this.currency;
		return new InternationalInventoryItemBean(label, price);
	}

}

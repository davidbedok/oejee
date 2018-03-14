package hu.qwaevisz.inventory.ejbservice.converter;

import java.util.List;
import java.util.stream.Collectors;

import hu.qwaevisz.inventory.ejbservice.domain.InventoryItemBean;
import hu.qwaevisz.inventory.persistence.domain.InventoryItem;

public class InventoryItemBeanConverterImpl implements InventoryItemBeanConverter {

	@Override
	public InventoryItemBean to(InventoryItem item) {
		return new InventoryItemBean(item.getReference(), item.getName(), item.getType().toString(), item.getValue());
	}

	@Override
	public List<InventoryItemBean> to(List<InventoryItem> items) {
		return items.stream().map(this::to).collect(Collectors.toList());
	}

}

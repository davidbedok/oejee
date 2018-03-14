package hu.qwaevisz.inventory.ejbservice.converter;

import java.util.List;

import hu.qwaevisz.inventory.ejbservice.domain.InventoryItemBean;
import hu.qwaevisz.inventory.persistence.domain.InventoryItem;

public interface InventoryItemBeanConverter {

	InventoryItemBean to(InventoryItem item);

	List<InventoryItemBean> to(List<InventoryItem> items);

}

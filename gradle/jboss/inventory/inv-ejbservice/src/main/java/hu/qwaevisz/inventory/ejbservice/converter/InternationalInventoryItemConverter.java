package hu.qwaevisz.inventory.ejbservice.converter;

import hu.qwaevisz.inventory.ejbservice.domain.InternationalInventoryItemBean;
import hu.qwaevisz.inventory.persistence.domain.InventoryItem;

public interface InternationalInventoryItemConverter {

	InternationalInventoryItemBean to(InventoryItem item);

}

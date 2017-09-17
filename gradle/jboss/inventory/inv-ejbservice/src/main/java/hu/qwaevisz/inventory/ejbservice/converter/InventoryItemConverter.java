package hu.qwaevisz.inventory.ejbservice.converter;

import hu.qwaevisz.inventory.ejbservice.domain.InventoryItemStub;
import hu.qwaevisz.inventory.persistence.domain.InventoryItem;

public interface InventoryItemConverter {

	InventoryItemStub to(InventoryItem item);

}

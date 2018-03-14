package hu.qwaevisz.inventory.persistence.holder;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.inventory.persistence.domain.InventoryItem;

@Local
public interface InventoryHolder {

	List<InventoryItem> getAll();

}

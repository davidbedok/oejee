package hu.qwaevisz.inventory.persistence.service;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.inventory.persistence.domain.InventoryItem;
import hu.qwaevisz.inventory.persistence.domain.InventoryType;

@Local
public interface InventorySearch {

	List<InventoryItem> list(InventoryType type, String nameTerm);

}

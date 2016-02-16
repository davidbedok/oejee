package hu.qwaevisz.inventory.ejbservice.facade;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;
import hu.qwaevisz.inventory.persistence.domain.Inventory;
import hu.qwaevisz.inventory.persistence.domain.InventoryType;

@Local
public interface InventoryFacade {

	Inventory getInventory(String reference) throws AdaptorException;

	List<Inventory> getInventories(InventoryType type) throws AdaptorException;

	List<Inventory> getInventories(InventoryType type, String nameTerm) throws AdaptorException;

}

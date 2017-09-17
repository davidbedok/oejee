package hu.qwaevisz.inventory.ejbservice.facade;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.inventory.ejbservice.domain.InventoryItemStub;
import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;
import hu.qwaevisz.inventory.persistence.domain.InventoryItem;
import hu.qwaevisz.inventory.persistence.domain.InventoryType;

@Local
public interface InventoryFacade {

	void test();

	InventoryItem getInventory(String reference) throws AdaptorException;

	List<InventoryItem> getInventories(InventoryType type) throws AdaptorException;

	List<InventoryItem> getInventories(InventoryType type, String nameTerm) throws AdaptorException;

	String getHost() throws AdaptorException;

	InventoryItemStub getInventoryStub(String reference) throws AdaptorException;

	List<Integer> getRandomNumbers(int quantity) throws AdaptorException;

}

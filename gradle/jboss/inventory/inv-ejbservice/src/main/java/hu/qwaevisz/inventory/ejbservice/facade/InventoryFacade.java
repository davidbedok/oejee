package hu.qwaevisz.inventory.ejbservice.facade;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.inventory.ejbservice.domain.InternationalInventoryItemBean;
import hu.qwaevisz.inventory.ejbservice.domain.InventoryItemBean;
import hu.qwaevisz.inventory.ejbservice.domain.PricingStrategy;
import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;
import hu.qwaevisz.inventory.persistence.domain.InventoryType;

@Local
public interface InventoryFacade {

	InventoryItemBean getInventoryItem(String reference) throws AdaptorException;

	List<InventoryItemBean> getInventoryItems(InventoryType type) throws AdaptorException;

	List<InventoryItemBean> getInventoryItems(InventoryType type, String nameTerm) throws AdaptorException;

	InventoryItemBean buyInventoryItem(String reference) throws AdaptorException;

	InventoryItemBean buyInventoryItem(String reference, PricingStrategy pricing) throws AdaptorException;

	InternationalInventoryItemBean getInternationalInventoryItem(String reference) throws AdaptorException;

	InternationalInventoryItemBean getInternationalInventoryItemWithEvent(String reference) throws AdaptorException;

	List<Integer> getRandomNumbers(int quantity) throws AdaptorException;

	String getHost() throws AdaptorException;

}

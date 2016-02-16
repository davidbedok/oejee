package hu.qwaevisz.inventory.ejbservice.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import hu.qwaevisz.inventory.ejbservice.client.ClientHolder;
import hu.qwaevisz.inventory.ejbservice.cost.CostService;
import hu.qwaevisz.inventory.ejbservice.event.NotifierEvent;
import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;
import hu.qwaevisz.inventory.ejbservice.interceptor.Logged;
import hu.qwaevisz.inventory.ejbservice.qualifier.ClientFlag;
import hu.qwaevisz.inventory.ejbservice.qualifier.Discount;
import hu.qwaevisz.inventory.ejbservice.qualifier.Random;
import hu.qwaevisz.inventory.persistence.domain.Client;
import hu.qwaevisz.inventory.persistence.domain.ClientType;
import hu.qwaevisz.inventory.persistence.domain.Inventory;
import hu.qwaevisz.inventory.persistence.domain.InventoryType;
import hu.qwaevisz.inventory.persistence.service.InventoryHolder;
import hu.qwaevisz.inventory.persistence.service.InventorySearch;

@Stateless(mappedName = "ejb/inventoryFacade")
public class InventoryFacadeImpl implements InventoryFacade {

	@Inject
	@Discount
	private CostService costService;

	@Inject
	@ClientFlag(ClientType.LIVE)
	private ClientHolder clientHolder;

	@Inject
	@Random
	private Instance<Integer> randomNumber;

	@Inject
	private Event<NotifierEvent> notifier;

	@EJB
	private InventoryHolder inventoryHolder;

	@EJB
	private InventorySearch inventorySearch;

	@Logged
	@Override
	public Inventory getInventory(String reference) throws AdaptorException {
		final Inventory inventory = this.inventoryHolder.get(reference);
		final int price = this.costService.getCost(inventory.getValue());
		final int uniquePrice = price + this.randomNumber.get() + this.randomNumber.get();
		inventory.setValue(uniquePrice);
		final Client client = this.clientHolder.getCurrent();
		this.notifier.fire(new NotifierEvent(client, "Get " + inventory.getName() + " (ref: " + inventory.getReference() + ") inventory item."));
		return inventory;
	}

	@Logged
	@Override
	public List<Inventory> getInventories(InventoryType type) throws AdaptorException {
		final List<Inventory> items = this.inventoryHolder.list(type);
		final Client client = this.clientHolder.getCurrent();
		this.notifier.fire(new NotifierEvent(client, "List " + items.size() + " inventory item(s)."));
		return items;
	}

	@Override
	public List<Inventory> getInventories(InventoryType type, String nameTerm) throws AdaptorException {
		return this.inventorySearch.list(type, nameTerm);
	}

}

package hu.qwaevisz.inventory.ejbservice.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import hu.qwaevisz.inventory.ejbservice.client.ClientHolder;
import hu.qwaevisz.inventory.ejbservice.converter.InventoryItemConverter;
import hu.qwaevisz.inventory.ejbservice.cost.CostService;
import hu.qwaevisz.inventory.ejbservice.domain.Client;
import hu.qwaevisz.inventory.ejbservice.domain.ClientType;
import hu.qwaevisz.inventory.ejbservice.domain.InventoryItemStub;
import hu.qwaevisz.inventory.ejbservice.event.NotifierEvent;
import hu.qwaevisz.inventory.ejbservice.exception.AdaptorException;
import hu.qwaevisz.inventory.ejbservice.interceptor.Logged;
import hu.qwaevisz.inventory.ejbservice.qualifier.ClientFlag;
import hu.qwaevisz.inventory.ejbservice.qualifier.Discount;
import hu.qwaevisz.inventory.ejbservice.qualifier.Random;
import hu.qwaevisz.inventory.ejbservice.service.InventoryConfiguration;
import hu.qwaevisz.inventory.persistence.domain.InventoryItem;
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
	@ClientFlag(ClientType.CUSTOM)
	private ClientHolder customClientHolder;

	@Inject
	private InventoryConfiguration configuration;

	@Inject
	@Random
	private Instance<Integer> randomNumber;

	@Inject
	private Event<NotifierEvent> notifier;

	@EJB
	private InventoryHolder inventoryHolder;

	@EJB
	private InventorySearch inventorySearch;

	@Inject
	private InventoryItemConverter converter;

	@Logged
	@Override
	public InventoryItem getInventory(final String reference) throws AdaptorException {
		final InventoryItem inventory = this.inventoryHolder.get(reference);
		inventory.setValue(this.costService.getCost(inventory.getValue()));
		final Client client = this.clientHolder.getCurrent();
		this.notifier.fire(new NotifierEvent(client, "Get " + inventory.getName() + " (ref: " + inventory.getReference() + ") inventory item."));
		this.notifier.fire(new NotifierEvent(this.customClientHolder.getCurrent(),
				"Get " + inventory.getName() + " (ref: " + inventory.getReference() + ") inventory item."));
		return inventory;
	}

	@Override
	public InventoryItemStub getInventoryStub(final String reference) throws AdaptorException {
		return this.converter.to(this.inventoryHolder.get(reference));
	}

	@Logged
	@Override
	public List<InventoryItem> getInventories(final InventoryType type) throws AdaptorException {
		final List<InventoryItem> items = this.inventoryHolder.list(type);
		final Client client = this.clientHolder.getCurrent();
		this.notifier.fire(new NotifierEvent(client, "List " + items.size() + " inventory item(s)."));
		return items;
	}

	@Override
	public List<InventoryItem> getInventories(final InventoryType type, final String nameTerm) throws AdaptorException {
		return this.inventorySearch.list(type, nameTerm);
	}

	@Override
	public String getHost() throws AdaptorException {
		return this.configuration.getHost();
	}

	@Logged
	@Override
	public List<Integer> getRandomNumbers(final int quantity) throws AdaptorException {
		final List<Integer> result = new ArrayList<>();
		for (int i = 0; i < quantity; i++) {
			result.add(this.randomNumber.get());
		}
		return result;
	}

}

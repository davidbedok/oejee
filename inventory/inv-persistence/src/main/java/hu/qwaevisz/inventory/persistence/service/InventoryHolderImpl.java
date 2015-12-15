package hu.qwaevisz.inventory.persistence.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import hu.qwaevisz.inventory.persistence.domain.Inventory;
import hu.qwaevisz.inventory.persistence.domain.InventoryType;

@Startup
@Singleton
public class InventoryHolderImpl implements InventoryHolder {

	private List<Inventory> items;

	@PostConstruct
	private void init() {
		this.items = new ArrayList<>();
		this.items.add(new Inventory("LOR42", "Lorem", InventoryType.BOOK, 42));
		this.items.add(new Inventory("IPS65", "Ipsum", InventoryType.DISK, 65));
		this.items.add(new Inventory("DOL30", "Dolor", InventoryType.CASSETTE, 30));
		this.items.add(new Inventory("SIT78", "Sit", InventoryType.BOOK, 78));
		this.items.add(new Inventory("AME85", "Amet", InventoryType.DISK, 85));
		this.items.add(new Inventory("CON01", "Consectetur", InventoryType.DISK, 1));
		this.items.add(new Inventory("ADI03", "Adipiscing", InventoryType.CASSETTE, 3));
	}

	@Override
	@Lock(LockType.READ)
	public Inventory get(String reference) {
		Inventory result = null;
		for (final Inventory current : this.items) {
			if (current.getReference().equals(reference)) {
				result = current;
				break;
			}
		}
		return result;
	}

	@Override
	@Lock(LockType.READ)
	public List<Inventory> list(InventoryType type) {
		final List<Inventory> result = new ArrayList<>();
		for (final Inventory current : this.items) {
			if (current.getType() == type) {
				result.add(current);
			}
		}
		return result;
	}

}

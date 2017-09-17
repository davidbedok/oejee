package hu.qwaevisz.inventory.persistence.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import hu.qwaevisz.inventory.persistence.domain.InventoryItem;
import hu.qwaevisz.inventory.persistence.domain.InventoryType;

@Startup
@Singleton
public class InventoryHolderImpl implements InventoryHolder {

	private List<InventoryItem> items;

	@PostConstruct
	private void init() {
		this.items = new ArrayList<>();
		this.items.add(new InventoryItem("LOR42", "Lorem10", InventoryType.BOOK, 42));
		this.items.add(new InventoryItem("LOR78", "Lorem20", InventoryType.BOOK, 78));
		this.items.add(new InventoryItem("LOR34", "Lorem30", InventoryType.BOOK, 34));
		this.items.add(new InventoryItem("IPS65", "Ipsum", InventoryType.DISK, 65));
		this.items.add(new InventoryItem("DOL30", "Dolor", InventoryType.CASSETTE, 30));
		this.items.add(new InventoryItem("SIT78", "Sit", InventoryType.BOOK, 78));
		this.items.add(new InventoryItem("AME85", "Amet", InventoryType.DISK, 85));
		this.items.add(new InventoryItem("CON01", "Consectetur", InventoryType.DISK, 1));
		this.items.add(new InventoryItem("ADI03", "Adipiscing", InventoryType.CASSETTE, 3));
	}

	@Override
	@Lock(LockType.READ)
	public InventoryItem get(String reference) {
		InventoryItem result = null;
		for (final InventoryItem current : this.items) {
			if (current.getReference().equals(reference)) {
				result = current;
				break;
			}
		}
		return result;
	}

	@Override
	@Lock(LockType.READ)
	public List<InventoryItem> list(InventoryType type) {
		final List<InventoryItem> result = new ArrayList<>();
		for (final InventoryItem current : this.items) {
			if (current.getType() == type) {
				result.add(current);
			}
		}
		return result;
	}

	@Override
	public List<InventoryItem> getAll() {
		return this.items;
	}

}

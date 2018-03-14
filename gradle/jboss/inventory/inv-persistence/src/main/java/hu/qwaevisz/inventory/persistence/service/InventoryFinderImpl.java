package hu.qwaevisz.inventory.persistence.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import hu.qwaevisz.inventory.persistence.domain.InventoryItem;
import hu.qwaevisz.inventory.persistence.domain.InventoryType;
import hu.qwaevisz.inventory.persistence.holder.InventoryHolder;

@Stateless
public class InventoryFinderImpl implements InventoryFinder {

	@EJB
	private InventoryHolder holder;

	@Override
	public InventoryItem get(String reference) {
		return this.holder.getAll().stream().filter(item -> item.getReference().equals(reference)).findFirst().orElse(null);
	}

	@Override
	public List<InventoryItem> list(InventoryType type) {
		return this.holder.getAll().stream().filter(item -> item.getType() == type).collect(Collectors.toList());
	}

}

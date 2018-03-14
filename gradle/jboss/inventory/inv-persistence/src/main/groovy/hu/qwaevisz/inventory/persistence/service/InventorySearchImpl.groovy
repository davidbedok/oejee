package hu.qwaevisz.inventory.persistence.service

import hu.qwaevisz.inventory.persistence.domain.InventoryItem
import hu.qwaevisz.inventory.persistence.domain.InventoryType
import hu.qwaevisz.inventory.persistence.holder.InventoryHolder

import javax.ejb.EJB
import javax.ejb.Stateless

@Stateless
class InventorySearchImpl implements InventorySearch {

	@EJB
	private InventoryHolder holder

	@Override
	List<InventoryItem> list(InventoryType type, String nameTerm) {
		holder.getAll().findAll { it -> it.type == type && it.name.startsWith(nameTerm) }
	}
}

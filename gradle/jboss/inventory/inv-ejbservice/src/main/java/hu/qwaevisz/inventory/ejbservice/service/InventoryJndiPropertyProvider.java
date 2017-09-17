package hu.qwaevisz.inventory.ejbservice.service;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

public class InventoryJndiPropertyProvider {

	@Resource(lookup = "java:global/inventoryhost")
	private String inventoryHost;

	@Resource(lookup = "java:global/currency")
	private String currency;

	@Produces
	@Named("host")
	public String getInventoryHost() {
		return this.inventoryHost;
	}

	@Produces
	@Named("currency")
	public String getCurrency() {
		return this.currency;
	}

}

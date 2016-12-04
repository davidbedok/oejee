package hu.qwaevisz.inventory.ejbservice.service;

public class InventoryConfiguration {

	private final String host;
	private final String currency;

	public InventoryConfiguration(String host, String currency) {
		this.host = host;
		this.currency = currency;
	}

	public String getHost() {
		return this.host;
	}

	public String getCurrency() {
		return this.currency;
	}

}

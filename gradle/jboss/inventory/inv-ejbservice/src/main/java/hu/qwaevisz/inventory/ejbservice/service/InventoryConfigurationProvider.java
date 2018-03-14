package hu.qwaevisz.inventory.ejbservice.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@ApplicationScoped
public class InventoryConfigurationProvider {

	@Produces
	public InventoryConfiguration config(@Named("host") String host, @Named("currency") String currency) {
		return new InventoryConfiguration(host, currency);
	}

}

package hu.qwaevisz.inventory.ejbservice.client;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import hu.qwaevisz.inventory.ejbservice.domain.ClientType;
import hu.qwaevisz.inventory.ejbservice.qualifier.ClientFlag;

@ApplicationScoped
public class CustomClientFactory {

	@Resource(lookup = "java:global/customClientName")
	private String customClientName;

	@Produces
	@ClientFlag(ClientType.CUSTOM)
	public ClientHolder getCustomClient() {
		return new CustomClientHolder(this.customClientName);
	}

}

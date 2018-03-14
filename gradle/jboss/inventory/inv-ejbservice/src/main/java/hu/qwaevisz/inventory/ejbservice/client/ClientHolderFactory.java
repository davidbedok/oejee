package hu.qwaevisz.inventory.ejbservice.client;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import hu.qwaevisz.inventory.ejbservice.domain.ClientType;
import hu.qwaevisz.inventory.ejbservice.qualifier.ClientFlag;

@ApplicationScoped
public class ClientHolderFactory {

	@Resource(lookup = "java:global/customClientName")
	private String clientName;

	@Produces
	@ClientFlag(ClientType.CUSTOM)
	public ClientHolder getCustomClientHolder() {
		return new CustomClientHolder(this.clientName);
	}

}

package hu.qwaevisz.inventory.ejbservice.client;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import hu.qwaevisz.inventory.ejbservice.qualifier.ClientFlag;
import hu.qwaevisz.inventory.persistence.domain.ClientType;

@ApplicationScoped
public class ClientServiceFactory {

	@Produces
	@ClientFlag(ClientType.FAKE)
	public ClientHolder getFakeClient() {
		return new FakeClientHolder();
	}

	@Produces
	@ClientFlag(ClientType.LIVE)
	public ClientHolder getLiveClient() {
		return new LiveClientHolder();
	}

}

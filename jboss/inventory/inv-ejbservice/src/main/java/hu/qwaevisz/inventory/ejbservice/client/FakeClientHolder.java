package hu.qwaevisz.inventory.ejbservice.client;

import hu.qwaevisz.inventory.ejbservice.domain.Client;
import hu.qwaevisz.inventory.ejbservice.domain.ClientType;
import hu.qwaevisz.inventory.ejbservice.qualifier.ClientFlag;

@ClientFlag(ClientType.FAKE)
public class FakeClientHolder implements ClientHolder {

	@Override
	public Client getCurrent() {
		return new Client("SVW987", "Scott V. Wright");
	}

}

package hu.qwaevisz.inventory.ejbservice.client;

import hu.qwaevisz.inventory.persistence.domain.Client;

public class FakeClientHolder implements ClientHolder {

	@Override
	public Client getCurrent() {
		return new Client("SVW987", "Scott V. Wright");
	}

}

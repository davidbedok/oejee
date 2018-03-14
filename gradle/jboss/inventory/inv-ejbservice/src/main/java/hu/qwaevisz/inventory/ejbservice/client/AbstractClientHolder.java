package hu.qwaevisz.inventory.ejbservice.client;

import hu.qwaevisz.inventory.ejbservice.domain.Client;

public abstract class AbstractClientHolder implements ClientHolder {

	private final Client client;

	public AbstractClientHolder(final String reference, String name) {
		this.client = new Client(reference, name);
	}

	@Override
	public Client getClient() {
		return this.client;
	}

}

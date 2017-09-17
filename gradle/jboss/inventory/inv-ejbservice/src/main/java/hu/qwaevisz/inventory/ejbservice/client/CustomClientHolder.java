package hu.qwaevisz.inventory.ejbservice.client;

import hu.qwaevisz.inventory.ejbservice.domain.Client;

public class CustomClientHolder implements ClientHolder {

	private final Client client;

	public CustomClientHolder(final String clientName) {
		final String reference = clientName.substring(0, 3).toUpperCase();
		this.client = new Client(reference, clientName);
	}

	@Override
	public Client getCurrent() {
		return this.client;
	}

}

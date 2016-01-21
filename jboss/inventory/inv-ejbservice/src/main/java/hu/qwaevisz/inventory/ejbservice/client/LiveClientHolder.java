package hu.qwaevisz.inventory.ejbservice.client;

import hu.qwaevisz.inventory.persistence.domain.Client;

public class LiveClientHolder implements ClientHolder {

	@Override
	public Client getCurrent() {
		return new Client("MCF012", "Matthew C. Flores");
	}

}

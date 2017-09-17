package hu.qwaevisz.inventory.ejbservice.client;

import hu.qwaevisz.inventory.ejbservice.domain.Client;
import hu.qwaevisz.inventory.ejbservice.domain.ClientType;
import hu.qwaevisz.inventory.ejbservice.qualifier.ClientFlag;

@ClientFlag(ClientType.LIVE)
public class LiveClientHolder implements ClientHolder {

	@Override
	public Client getCurrent() {
		return new Client("MCF012", "Matthew C. Flores");
	}

}

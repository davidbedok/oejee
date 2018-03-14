package hu.qwaevisz.inventory.ejbservice.client;

import hu.qwaevisz.inventory.ejbservice.domain.ClientType;
import hu.qwaevisz.inventory.ejbservice.qualifier.ClientFlag;

@ClientFlag(ClientType.TEST)
public class TestClientHolder extends AbstractClientHolder {

	public TestClientHolder() {
		super("TEST", "Test System");
	}

}

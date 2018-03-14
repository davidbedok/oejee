package hu.qwaevisz.inventory.ejbservice.client;

import hu.qwaevisz.inventory.ejbservice.domain.ClientType;
import hu.qwaevisz.inventory.ejbservice.qualifier.ClientFlag;

@ClientFlag(ClientType.SANDBOX)
public class SandboxClientHolder extends AbstractClientHolder {

	public SandboxClientHolder() {
		super("SVW987", "Scott V. Wright");
	}

}

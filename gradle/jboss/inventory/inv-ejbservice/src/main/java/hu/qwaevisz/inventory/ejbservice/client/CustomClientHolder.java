package hu.qwaevisz.inventory.ejbservice.client;

public class CustomClientHolder extends AbstractClientHolder {

	public CustomClientHolder(final String name) {
		super(name.substring(0, 3).toUpperCase(), name);
	}

}

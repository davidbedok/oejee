package hu.qwaevisz.inventory.ejbservice.domain;

public class Client {

	private final String reference;
	private final String name;

	public Client(final String reference, String name) {
		this.reference = reference;
		this.name = name;
	}

	public String getReference() {
		return this.reference;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return "Client [reference=" + this.reference + ", name=" + this.name + "]";
	}

}

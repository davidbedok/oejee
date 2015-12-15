package hu.qwaevisz.inventory.ejbservice.event;

import java.io.Serializable;

import hu.qwaevisz.inventory.persistence.domain.Client;

public class NotifierEvent implements Serializable {

	private static final long serialVersionUID = -4689576325364383690L;

	public Client client;
	public String message;

	public NotifierEvent() {

	}

	public NotifierEvent(Client client, String message) {
		this.client = client;
		this.message = message;
	}

	@Override
	public String toString() {
		return "NotifierEvent [client=" + this.client + ", message=" + this.message + "]";
	}

}

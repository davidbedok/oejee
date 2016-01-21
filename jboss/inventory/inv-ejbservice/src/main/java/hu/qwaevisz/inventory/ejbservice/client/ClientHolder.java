package hu.qwaevisz.inventory.ejbservice.client;

import hu.qwaevisz.inventory.persistence.domain.Client;

public interface ClientHolder {

	Client getCurrent();

}

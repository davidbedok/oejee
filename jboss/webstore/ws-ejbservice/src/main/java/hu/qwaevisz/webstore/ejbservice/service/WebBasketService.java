package hu.qwaevisz.webstore.ejbservice.service;

import javax.ejb.Local;

import hu.qwaevisz.webstore.common.doclet.Description;
import hu.qwaevisz.webstore.ejbservice.domain.Basket;
import hu.qwaevisz.webstore.ejbservice.domain.Product;
import hu.qwaevisz.webstore.ejbservice.exception.ServiceException;

@Local
@Description("This Basket service is good for handling customer purchase process.")
public interface WebBasketService {

	@Description("Use that method when a new customer appears in the webstore.")
	void setIdentifier(@Description("Unique identifier of the customer.") String identifier) throws ServiceException;

	@Description("Retrieve the identifier of the current customer.")
	String getIdentifier() throws ServiceException;

	@Description("Give you information about the size of the basket.")
	int getBasketSize() throws ServiceException;

	@Description("Use that method if the customer buys something new in the webstore.")
	void addItem(@Description("The product which is put into the webbasket.") Product product) throws ServiceException;

	@Description("Provides all the available information about the basket.")
	Basket getContent() throws ServiceException;

}

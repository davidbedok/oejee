package hu.qwaevisz.webstore.ejbservice.service;

import javax.ejb.Local;

import hu.qwaevisz.webstore.common.doclet.Description;
import hu.qwaevisz.webstore.ejbservice.domain.Basket;
import hu.qwaevisz.webstore.ejbservice.exception.ServiceException;

@Local
@Description("This Basket service is good for handling customer's purchase process.")
public interface WebBasketService {

	@Description("Use that method to set the name of the basket.")
	void setIdentifier(@Description("Name of the basket.") String identifier) throws ServiceException;

	@Description("Retrieve the identifier/name of the customer's basket.")
	String getIdentifier() throws ServiceException;

	@Description("Give you information about the size of the basket.")
	int getBasketSize() throws ServiceException;

	@Description("Use that method if the customer buys something new in the webstore or increase the quantity of the given product in his/her basket.")
	void addItem(@Description("The product name which is put into the webbasket.") String productName) throws ServiceException;

	@Description("Use that method if the customer decrease the quantity of the given product from his/her basket.")
	void removeItem(@Description("The name of the product") String productName) throws ServiceException;

	@Description("Provides all the available information about the basket.")
	Basket getContent() throws ServiceException;

	void remove();

}

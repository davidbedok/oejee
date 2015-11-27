package hu.qwaevisz.webstore.ejbservice.service;

import javax.ejb.Local;

import hu.qwaevisz.webstore.ejbservice.domain.Basket;
import hu.qwaevisz.webstore.ejbservice.domain.Product;
import hu.qwaevisz.webstore.ejbservice.exception.ServiceException;

@Local
public interface WebBasketService {

	void setIdentifier(String identifier) throws ServiceException;

	String getIdentifier() throws ServiceException;

	int getBasketSize() throws ServiceException;

	void addItem(Product product) throws ServiceException;

	Basket getContent() throws ServiceException;

}

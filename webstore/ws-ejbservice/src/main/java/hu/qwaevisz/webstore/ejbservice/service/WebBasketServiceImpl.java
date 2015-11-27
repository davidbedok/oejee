package hu.qwaevisz.webstore.ejbservice.service;

import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.apache.log4j.Logger;

import hu.qwaevisz.webstore.ejbservice.domain.Basket;
import hu.qwaevisz.webstore.ejbservice.domain.Product;
import hu.qwaevisz.webstore.ejbservice.exception.ServiceException;

@Stateful(mappedName = "ejb/webBasketService")
public class WebBasketServiceImpl implements WebBasketService {

	private static final Logger LOGGER = Logger.getLogger(WebBasketServiceImpl.class);

	private Basket basket;

	@PostConstruct
	public void setup() {
		this.basket = new Basket();
	}

	@Override
	public void setIdentifier(String identifier) throws ServiceException {
		LOGGER.debug("Set the identifier (" + identifier + ") for " + this.basket);
		if (!this.basket.hasIdentifier()) {
			this.basket.setIdentifier(identifier);
		} else {
			throw new ServiceException(42, "Basket already has an identifier (" + this.basket.getIdentifier() + ").");
		}
	}

	@Override
	public String getIdentifier() throws ServiceException {
		LOGGER.debug("Get the identifier of the " + this.basket);
		return this.basket.getIdentifier();
	}

	@Override
	public int getBasketSize() throws ServiceException {
		LOGGER.debug("Get the size of the " + this.basket);
		return this.basket.getSize();
	}

	@Override
	public void addItem(Product product) throws ServiceException {
		LOGGER.debug("Add Product (" + product + ") to " + this.basket);
		if (this.basket.find(product)) {
			this.basket.increment(product);
		} else {
			this.basket.add(product);
		}
	}

	@Override
	public Basket getContent() throws ServiceException {
		LOGGER.debug("Get " + this.basket);
		return this.basket;
	}

	@Remove
	public void remove() {
		this.basket = null;
	}

}

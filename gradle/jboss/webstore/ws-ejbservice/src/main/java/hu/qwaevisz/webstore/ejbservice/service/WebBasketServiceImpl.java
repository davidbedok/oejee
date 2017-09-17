package hu.qwaevisz.webstore.ejbservice.service;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.apache.log4j.Logger;

import hu.qwaevisz.webstore.ejbservice.converter.ProductConverter;
import hu.qwaevisz.webstore.ejbservice.domain.Basket;
import hu.qwaevisz.webstore.ejbservice.domain.ProductStub;
import hu.qwaevisz.webstore.ejbservice.exception.ServiceException;
import hu.qwaevisz.webstore.ejbservice.exception.WebStoreError;
import hu.qwaevisz.webstore.persistence.exception.PersistenceException;
import hu.qwaevisz.webstore.persistence.service.PersistenceService;

@Stateful(mappedName = "ejb/webBasketService")
public class WebBasketServiceImpl implements WebBasketService {

	private static final Logger LOGGER = Logger.getLogger(WebBasketServiceImpl.class);

	private Basket basket;

	@EJB
	private PersistenceService service;

	@EJB
	private ProductConverter converter;

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
			throw new ServiceException(WebStoreError.IDENTIFIER, "Basket already has an identifier (" + this.basket.getIdentifier() + ").");
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
	public void addItem(String productName) throws ServiceException {
		LOGGER.debug("Add Product (" + productName + ") to " + this.basket);
		try {
			ProductStub product = this.converter.toStub(this.service.read(productName));
			if (product != null) {
				if (this.basket.find(product)) {
					this.basket.increment(product);
				} else {
					this.basket.add(product);
				}
			} else {
				throw new ServiceException(WebStoreError.PRODUCT, "Unknown product (name: " + productName + ").");
			}
		} catch (PersistenceException e) {
			LOGGER.error(e, e);
			throw new ServiceException(WebStoreError.PERSISTENCE, e.getMessage());
		}
	}

	@Override
	public void removeItem(String productName) throws ServiceException {
		LOGGER.debug("Remove Product (" + productName + ") from " + this.basket);
		try {
			ProductStub product = this.converter.toStub(this.service.read(productName));
			if (product != null) {
				if (this.basket.find(product)) {
					this.basket.remove(product);
				} else {
					throw new ServiceException(WebStoreError.IDENTIFIER,
							"Basket (" + this.basket.getIdentifier() + ") doesn't contain this product (" + productName + ").");
				}
			} else {
				throw new ServiceException(WebStoreError.PRODUCT, "Unknown product (name: " + productName + ").");
			}
		} catch (PersistenceException e) {
			LOGGER.error(e, e);
			throw new ServiceException(WebStoreError.PERSISTENCE, e.getMessage());
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

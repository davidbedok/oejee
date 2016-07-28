package hu.qwaevisz.webstore.ejbservice.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hu.qwaevisz.webstore.ejbservice.converter.ProductConverter;
import hu.qwaevisz.webstore.ejbservice.domain.BrandStub;
import hu.qwaevisz.webstore.ejbservice.domain.ProductStub;
import hu.qwaevisz.webstore.ejbservice.exception.ServiceException;
import hu.qwaevisz.webstore.ejbservice.exception.WebStoreError;
import hu.qwaevisz.webstore.persistence.exception.PersistenceException;
import hu.qwaevisz.webstore.persistence.service.PersistenceService;

@Stateless(mappedName = "ejb/storeService")
public class StoreServiceImpl implements StoreService {

	private static final Logger LOGGER = Logger.getLogger(StoreServiceImpl.class);

	@EJB
	private PersistenceService service;

	@EJB
	private ProductConverter converter;

	@Override
	public void add(ProductStub product) throws ServiceException {
		try {
			LOGGER.debug("Add a new product (" + product + ")");
			this.service.create(this.converter.to(product));
		} catch (PersistenceException e) {
			throw new ServiceException(WebStoreError.PERSISTENCE, e.getMessage());
		}
	}

	@Override
	public ProductStub get(String name) throws ServiceException {
		try {
			LOGGER.debug("Get a product by name (" + name + ")");
			return this.converter.toStub(this.service.read(name));
		} catch (PersistenceException e) {
			throw new ServiceException(WebStoreError.PERSISTENCE, e.getMessage());
		}
	}

	@Override
	public List<ProductStub> list(BrandStub brand) throws ServiceException {
		try {
			LOGGER.debug("List products by brand (" + brand + ")");
			return this.converter.toStubs(this.service.readAll(this.converter.to(brand)));
		} catch (PersistenceException e) {
			throw new ServiceException(WebStoreError.PERSISTENCE, e.getMessage());
		}
	}

	@Override
	public List<ProductStub> getAll() throws ServiceException {
		try {
			LOGGER.debug("Get all products");
			return this.converter.toStubs(this.service.readAll());
		} catch (PersistenceException e) {
			throw new ServiceException(WebStoreError.PERSISTENCE, e.getMessage());
		}
	}

	@Override
	public List<ProductStub> list(String nameTerm) throws ServiceException {
		try {
			LOGGER.debug("List products by name term (" + nameTerm + ")");
			return this.converter.toStubs(this.service.readAll(nameTerm));
		} catch (PersistenceException e) {
			throw new ServiceException(WebStoreError.PERSISTENCE, e.getMessage());
		}
	}

	@Override
	public void remove(String name) throws ServiceException {
		try {
			LOGGER.debug("Delete a product by name (" + name + ")");
			this.service.delete(name);
		} catch (PersistenceException e) {
			throw new ServiceException(WebStoreError.PERSISTENCE, e.getMessage());
		}
	}

}

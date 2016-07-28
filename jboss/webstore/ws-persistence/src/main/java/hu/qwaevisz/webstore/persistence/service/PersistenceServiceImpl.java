package hu.qwaevisz.webstore.persistence.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hu.qwaevisz.webstore.persistence.domain.Brand;
import hu.qwaevisz.webstore.persistence.domain.Product;
import hu.qwaevisz.webstore.persistence.exception.PersistenceException;

@Stateless
public class PersistenceServiceImpl implements PersistenceService {

	private static final Logger LOGGER = Logger.getLogger(PersistenceServiceImpl.class);

	@EJB
	private StoreHolder holder;

	@Override
	public void create(Product product) throws PersistenceException {
		try {
			this.holder.create(product);
		} catch (Exception e) {
			LOGGER.error(e, e);
			throw new PersistenceException(e.getMessage());
		}
	}

	@Override
	public Product read(String name) throws PersistenceException {
		Product result = this.holder.read(name);
		if (result == null) {
			throw new PersistenceException("Product does not exist in the catalog (name: " + name + ").");
		}
		return result;
	}

	@Override
	public List<Product> readAll(String nameTerm) throws PersistenceException {
		return this.holder.readAll(nameTerm);
	}

	@Override
	public List<Product> readAll(Brand brand) throws PersistenceException {
		return this.holder.readAll(brand);
	}

	@Override
	public List<Product> readAll() throws PersistenceException {
		return this.holder.readAll();
	}

	@Override
	public void delete(String name) throws PersistenceException {
		try {
			this.holder.delete(name);
		} catch (Exception e) {
			LOGGER.error(e, e);
			throw new PersistenceException(e.getMessage());
		}
	}

}

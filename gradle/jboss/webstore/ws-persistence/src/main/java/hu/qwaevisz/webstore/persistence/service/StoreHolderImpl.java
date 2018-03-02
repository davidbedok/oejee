package hu.qwaevisz.webstore.persistence.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.log4j.Logger;

import hu.qwaevisz.webstore.persistence.domain.Brand;
import hu.qwaevisz.webstore.persistence.domain.Product;

@Startup
@Singleton
public class StoreHolderImpl implements StoreHolder {

	private static final Logger LOGGER = Logger.getLogger(StoreHolderImpl.class);

	private List<Product> items;

	@PostConstruct
	private void init() {
		this.items = new ArrayList<>();
		this.items.add(new Product(Brand.SONY, "ZD9 4K HDR", 1499));
		this.items.add(new Product(Brand.SONY, "SD85 4K HDR", 1299));
		this.items.add(new Product(Brand.SONY, "XD83 4K HDR", 1299));
		this.items.add(new Product(Brand.PHILIPS, "40PFH5500 Smart Led", 999));
		this.items.add(new Product(Brand.PHILIPS, "55PUH6400 UHD Smart Led", 850));
		this.items.add(new Product(Brand.PANASONIC, "TX-40CS620E LED ", 1350));
		this.items.add(new Product(Brand.PANASONIC, "TX-58DX800E", 699));
		LOGGER.debug("Initial catalog was created.");
	}

	@Override
	@Lock(LockType.WRITE)
	public void create(Product product) {
		if (!this.items.contains(product)) {
			this.items.add(product);
			LOGGER.debug("New product (" + product + ") was added into the catalog.");
		} else {
			throw new IllegalArgumentException("Product has already registered in the catalog (" + product + ").");
		}
	}

	@Override
	@Lock(LockType.READ)
	public Product read(String name) {
		return this.items.stream().filter(product -> product.getName().equals(name)).findFirst().orElse(null);
	}

	@Override
	@Lock(LockType.READ)
	public List<Product> readAll(String nameTerm) {
		String ucNameTerm = nameTerm.toUpperCase();
		return this.items.stream().filter(product -> product.getName().toUpperCase().contains(ucNameTerm)).collect(Collectors.toList());
	}

	@Override
	@Lock(LockType.READ)
	public List<Product> readAll(Brand brand) {
		return this.items.stream().filter(product -> product.getBrand() == brand).collect(Collectors.toList());
	}

	@Override
	@Lock(LockType.READ)
	public List<Product> readAll() {
		return this.items;
	}

	@Override
	@Lock(LockType.WRITE)
	public void delete(String name) {
		Product product = this.read(name);
		if (product == null) {
			throw new IllegalArgumentException("Product is not exists in the catalog (name: " + name + ").");
		} else {
			this.items.remove(product);
			LOGGER.debug("Prodcut (" + product + ") was deleted from the catalog.");
		}
	}

}

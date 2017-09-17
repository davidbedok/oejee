package hu.qwaevisz.webstore.persistence.service;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.webstore.persistence.domain.Brand;
import hu.qwaevisz.webstore.persistence.domain.Product;

@Local
public interface StoreHolder {

	void create(Product product);

	Product read(String name);

	List<Product> readAll(String nameTerm);

	List<Product> readAll(Brand brand);

	List<Product> readAll();

	void delete(String name);

}

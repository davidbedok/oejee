package hu.qwaevisz.webstore.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import hu.qwaevisz.webstore.ejbservice.domain.BrandStub;
import hu.qwaevisz.webstore.ejbservice.domain.ProductStub;
import hu.qwaevisz.webstore.persistence.domain.Brand;
import hu.qwaevisz.webstore.persistence.domain.Product;

@Local
public interface ProductConverter {

	Brand to(BrandStub brand);

	BrandStub toStub(Brand brand);

	Product to(ProductStub product);

	ProductStub toStub(Product product);

	List<ProductStub> toStubs(List<Product> products);

	List<Product> to(List<ProductStub> products);

}

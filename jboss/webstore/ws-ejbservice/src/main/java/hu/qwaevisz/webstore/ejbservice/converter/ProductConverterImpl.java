package hu.qwaevisz.webstore.ejbservice.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import hu.qwaevisz.webstore.ejbservice.domain.BrandStub;
import hu.qwaevisz.webstore.ejbservice.domain.ProductStub;
import hu.qwaevisz.webstore.persistence.domain.Brand;
import hu.qwaevisz.webstore.persistence.domain.Product;

@Stateless(mappedName = "ejb/productConverter")
public class ProductConverterImpl implements ProductConverter {

	@Override
	public Brand to(BrandStub brand) {
		return Brand.valueOf(brand.name());
	}

	@Override
	public BrandStub toStub(Brand brand) {
		return BrandStub.valueOf(brand.name());
	}

	@Override
	public Product to(ProductStub product) {
		Product result = null;
		if (product != null) {
			Brand brand = this.to(product.getBrand());
			result = new Product(brand, product.getName(), product.getPrice());
		}
		return result;
	}

	@Override
	public ProductStub toStub(Product product) {
		ProductStub stub = null;
		if (product != null) {
			BrandStub brand = this.toStub(product.getBrand());
			stub = new ProductStub(brand, product.getName(), product.getPrice());
		}
		return stub;
	}

	@Override
	public List<ProductStub> toStubs(List<Product> products) {
		List<ProductStub> stubs = new ArrayList<>();
		for (Product product : products) {
			stubs.add(this.toStub(product));
		}
		return stubs;
	}

	@Override
	public List<Product> to(List<ProductStub> products) {
		List<Product> items = new ArrayList<>();
		for (ProductStub product : products) {
			items.add(this.to(product));
		}
		return items;
	}

}

package hu.qwaevisz.shopping.loader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hu.qwaevisz.shopping.loader.domain.Product;
import hu.qwaevisz.shopping.loader.domain.ProductCategory;

public class ProductCatalog {

	private final List<Product> items;
	private final Random random;

	public ProductCatalog(final Random random) {
		this.random = random;
		this.items = new ArrayList<>();
	}

	public void add(final long id, final String name, final ProductCategory category, final double price) {
		this.items.add(new Product(id, name, category, price));
	}

	public Product getRandomProduct() {
		return this.items.get(this.random.nextInt(this.items.size()));
	}

	@Override
	public String toString() {
		final StringBuilder info = new StringBuilder();
		for (final Product product : this.items) {
			info.append(product.toString()).append('\n');
		}
		return info.toString();
	}

}

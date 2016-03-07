package hu.qwaevisz.shopping.loader.domain;

public class Product {

	private long id;
	private String name;
	private ProductCategory category;
	private double price;

	public Product(final long id, final String name, final ProductCategory category, final double price) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
	}

	public long getId() {
		return this.id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public ProductCategory getCategory() {
		return this.category;
	}

	public void setCategory(final ProductCategory category) {
		this.category = category;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(final double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + this.id + ", name=" + this.name + ", category=" + this.category + ", price=" + this.price + "]";
	}

}

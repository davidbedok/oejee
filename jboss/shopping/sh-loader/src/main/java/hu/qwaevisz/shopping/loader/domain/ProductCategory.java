package hu.qwaevisz.shopping.loader.domain;

public enum ProductCategory {

	BAKERY("bakery"),
	COLD_CUTS("cold cuts"),
	DAIRY_PRODUCTS("dairy products"),
	BAKING("baking"),
	HOUSEHOLD("household");

	private final String label;

	private ProductCategory(final String label) {
		this.label = label;
	}

	public static ProductCategory fromLabel(final String label) {
		ProductCategory result = null;
		for (final ProductCategory category : ProductCategory.values()) {
			if (category.label.equals(label)) {
				result = category;
				break;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return this.label;
	}

}

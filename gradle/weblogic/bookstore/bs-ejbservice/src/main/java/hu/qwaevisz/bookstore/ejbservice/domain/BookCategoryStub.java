package hu.qwaevisz.bookstore.ejbservice.domain;

public enum BookCategoryStub {

	SCIFI("Sci-Fi"),
	LITERATURE("Literature"),
	HISTORICAL("Historical"),
	PHILOSOPHY("Philosophy");

	private final String label;

	private BookCategoryStub(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}

	public String getName() { return this.name(); }

}

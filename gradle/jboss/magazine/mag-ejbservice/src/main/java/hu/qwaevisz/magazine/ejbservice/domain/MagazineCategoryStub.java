package hu.qwaevisz.magazine.ejbservice.domain;

public enum MagazineCategoryStub {

	GAMES("Games"),
	LITERATURE("Literature"),
	HISTORICAL("Historical"),
	PHILOSOPHY("Philosophy");

	private final String label;

	private MagazineCategoryStub(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}

	public String getName() {
		return this.name();
	}

}

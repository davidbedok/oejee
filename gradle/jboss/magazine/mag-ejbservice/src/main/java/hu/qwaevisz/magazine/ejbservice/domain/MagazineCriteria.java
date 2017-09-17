package hu.qwaevisz.magazine.ejbservice.domain;

public class MagazineCriteria {

	private String publisher;
	private String title;
	private MagazineCategoryStub category;
	private int minimumPrice;
	private int maximumPrice;

	public MagazineCriteria() {
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MagazineCategoryStub getCategory() {
		return this.category;
	}

	public void setCategory(MagazineCategoryStub category) {
		this.category = category;
	}

	public int getMinimumPrice() {
		return this.minimumPrice;
	}

	public void setMinimumPrice(int minimumPrice) {
		this.minimumPrice = minimumPrice;
	}

	public int getMaximumPrice() {
		return this.maximumPrice;
	}

	public void setMaximumPrice(int maximumPrice) {
		this.maximumPrice = maximumPrice;
	}

	@Override
	public String toString() {
		return "MagazineCriteria [publisher=" + this.publisher + ", title=" + this.title + ", category=" + this.category + ", minimumPrice=" + this.minimumPrice
				+ ", maximumPrice=" + this.maximumPrice + "]";
	}

}

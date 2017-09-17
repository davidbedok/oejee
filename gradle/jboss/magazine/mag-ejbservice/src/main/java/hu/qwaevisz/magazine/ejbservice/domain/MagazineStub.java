package hu.qwaevisz.magazine.ejbservice.domain;

public class MagazineStub {

	private String reference;
	private String publisher;
	private String title;
	private MagazineCategoryStub category;
	private double price;
	private int numberOfPages;

	public MagazineStub() {
		this(null, null, null, null, 0, 0);
	}

	public MagazineStub(String reference, String publisher, String title, MagazineCategoryStub category, double price, int numberOfPages) {
		super();
		this.reference = reference;
		this.publisher = publisher;
		this.title = title;
		this.category = category;
		this.price = price;
		this.numberOfPages = numberOfPages;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
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

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNumberOfPages() {
		return this.numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	@Override
	public String toString() {
		return "MagazineStub [reference=" + this.reference + ", publisher=" + this.publisher + ", title=" + this.title + ", category=" + this.category
				+ ", price=" + this.price + ", numberOfPages=" + this.numberOfPages + "]";
	}

}

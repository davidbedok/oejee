package hu.qwaevisz.diskstore.ejbserviceclient.domain;

import java.io.Serializable;

public class DiskStub implements Serializable {

	private String reference;
	private String author;
	private String title;
	private DiskCategoryStub category;
	private double price;
	private int numberOfSongs;

	public DiskStub(final String reference, final String author, final String title, final DiskCategoryStub category, final double price,
			final int numberOfSongs) {
		super();
		this.reference = reference;
		this.author = author;
		this.title = title;
		this.category = category;
		this.price = price;
		this.numberOfSongs = numberOfSongs;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(final String reference) {
		this.reference = reference;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public DiskCategoryStub getCategory() {
		return this.category;
	}

	public void setCategory(final DiskCategoryStub category) {
		this.category = category;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(final double price) {
		this.price = price;
	}

	public int getNumberOfSongs() {
		return this.numberOfSongs;
	}

	public void setNumberOfSongs(final int numberOfSongs) {
		this.numberOfSongs = numberOfSongs;
	}

	@Override
	public String toString() {
		return "DiskStub [reference=" + this.reference + ", author=" + this.author + ", title=" + this.title + ", category=" + this.category + ", price="
				+ this.price + ", numberOfSongs=" + this.numberOfSongs + "]";
	}

}

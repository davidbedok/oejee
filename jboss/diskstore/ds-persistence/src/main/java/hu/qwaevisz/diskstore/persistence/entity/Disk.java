package hu.qwaevisz.diskstore.persistence.entity;

import org.apache.ibatis.type.Alias;

import hu.qwaevisz.diskstore.persistence.entity.trunk.DiskCategory;

@Alias("Disk")
public class Disk {

	private Integer id;
	private String reference;
	private String author;
	private String title;
	private DiskCategory category;
	private Double price;
	private Integer numberOfSongs;

	public Disk() {
		this(null, null, null, null, null, null);
	}

	public Disk(final String reference, final String author, final String title, final DiskCategory category, final Double price, final Integer numberOfSongs) {
		this.reference = reference;
		this.author = author;
		this.title = title;
		this.category = category;
		this.price = price;
		this.numberOfSongs = numberOfSongs;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
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

	public DiskCategory getCategory() {
		return this.category;
	}

	public void setCategory(final DiskCategory category) {
		this.category = category;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(final Double price) {
		this.price = price;
	}

	public Integer getNumberOfSongs() {
		return this.numberOfSongs;
	}

	public void setNumberOfSongs(final Integer numberOfSongs) {
		this.numberOfSongs = numberOfSongs;
	}

	@Override
	public String toString() {
		return "Disk [id=" + this.id + ", reference=" + this.reference + ", author=" + this.author + ", title=" + this.title + ", category=" + this.category
				+ ", price=" + this.price + ", numberOfSongs=" + this.numberOfSongs + "]";
	}

}

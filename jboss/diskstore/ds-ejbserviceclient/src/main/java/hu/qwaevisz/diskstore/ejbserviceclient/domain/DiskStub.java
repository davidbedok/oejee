package hu.qwaevisz.diskstore.ejbserviceclient.domain;

import java.io.Serializable;

public class DiskStub implements Serializable {

	private static final long serialVersionUID = 4438944490512543719L;

	private int diskId;
	private String author;
	private String title;
	private double price;

	public DiskStub() {
		this(0, null, null, 0);
	}

	public DiskStub(int diskId, String author, String title, double price) {
		super();
		this.diskId = diskId;
		this.author = author;
		this.title = title;
		this.price = price;
	}

	public int getDiskId() {
		return this.diskId;
	}

	public void setDiskId(int diskId) {
		this.diskId = diskId;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "DiskStub [diskId=" + this.diskId + ", author=" + this.author + ", title=" + this.title + ", price=" + this.price + "]";
	}

}

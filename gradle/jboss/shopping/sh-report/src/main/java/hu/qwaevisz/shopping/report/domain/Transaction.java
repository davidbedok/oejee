package hu.qwaevisz.shopping.report.domain;

import java.util.Date;

public class Transaction {

	private long id;
	private Date date;
	private String comment;
	private int numberOfItems;
	private int totalPrice;

	public Transaction(final long id, final Date date, final String comment, final int numberOfItems, final int totalPrice) {
		super();
		this.id = id;
		this.date = date;
		this.comment = comment;
		this.numberOfItems = numberOfItems;
		this.totalPrice = totalPrice;
	}

	public long getId() {
		return this.id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

	public int getNumberOfItems() {
		return this.numberOfItems;
	}

	public void setNumberOfItems(final int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public int getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(final int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + this.id + ", date=" + this.date + ", comment=" + this.comment + ", numberOfItems=" + this.numberOfItems + ", totalPrice="
				+ this.totalPrice + "]";
	}

}

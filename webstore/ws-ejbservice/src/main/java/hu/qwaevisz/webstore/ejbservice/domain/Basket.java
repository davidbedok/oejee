package hu.qwaevisz.webstore.ejbservice.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "basket")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Basket {

	private String identifier;
	private final List<BasketItem> items;

	public Basket() {
		this.items = new ArrayList<>();
	}

	public void add(Product product) {
		this.items.add(new BasketItem(product, 1));
	}

	public void increment(Product product) {
		for (final BasketItem item : this.items) {
			if (item.getProduct().equals(product)) {
				item.increment();
				break;
			}
		}
	}

	public boolean find(Product product) {
		boolean result = false;
		for (final BasketItem item : this.items) {
			if (item.getProduct().equals(product)) {
				result = true;
				break;
			}
		}
		return result;
	}

	@XmlAttribute(name = "identifier")
	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public boolean hasIdentifier() {
		return this.identifier != null;
	}

	@XmlElement(name = "items")
	public List<BasketItem> getItems() {
		return this.items;
	}

	public int getSize() {
		return this.items.size();
	}

	@XmlAttribute(name = "total")
	public int getTotal() {
		int sum = 0;
		for (final BasketItem item : this.items) {
			sum += item.getTotalPrice();
		}
		return sum;
	}

	@Override
	public String toString() {
		return "Basket [identifier=" + this.identifier + ", number of items: " + this.getSize() + "]";
	}

}

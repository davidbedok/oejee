package hu.qwaevisz.webstore.ejbservice.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import hu.qwaevisz.webstore.ejbservice.exception.ServiceException;
import hu.qwaevisz.webstore.ejbservice.exception.WebStoreError;

@XmlRootElement(name = "Basket")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Basket {

	private String identifier;
	private final List<BasketItem> items;

	public Basket() {
		this.items = new ArrayList<>();
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

	@XmlElement(name = "Item")
	public List<BasketItem> getItems() {
		return this.items;
	}

	public void increment(ProductStub product) {
		BasketItem item = this.findItem(product);
		if (item != null) {
			item.increment();
		} else {
			this.add(product);
		}
	}

	private BasketItem findItem(ProductStub product) {
		return this.items.stream().filter(bi -> bi.getProduct().equals(product)).findFirst().orElse(null);
	}

	private void add(ProductStub product) {
		this.items.add(new BasketItem(product, 1));
	}

	public void decrement(ProductStub product) throws ServiceException {
		BasketItem item = this.findItem(product);
		if (item != null) {
			item.decrement();
			if (item.getQuantity() == 0) {
				this.remove(product);
			}
		} else {
			throw new ServiceException(WebStoreError.BASKET, "Basket (" + this.identifier + ") does not contain this product (" + product.getName() + ").");
		}
	}

	private void remove(ProductStub product) {
		this.items.removeIf(bi -> bi.getProduct().equals(product));
	}

	public boolean find(ProductStub product) {
		// return this.items.stream().anyMatch(bi -> bi.getProduct().equals(product));
		return this.items.stream().map(BasketItem::getProduct).anyMatch(product::equals);
	}

	public int getSize() {
		return this.items.size();
	}

	@XmlAttribute(name = "total")
	public int getTotal() {
		return this.items.stream().mapToInt(BasketItem::getTotalPrice).sum();
	}

	@Override
	public String toString() {
		return "Basket [identifier=" + this.identifier + ", number of items: " + this.getSize() + "]";
	}

}

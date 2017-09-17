package hu.qwaevisz.webstore.ejbservice.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class BasketItem {

	private ProductStub product;
	private int quantity;

	public BasketItem() {

	}

	public BasketItem(ProductStub product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	@XmlElement(name = "Product")
	public ProductStub getProduct() {
		return this.product;
	}

	public void setProduct(ProductStub product) {
		this.product = product;
	}

	@XmlAttribute(name = "quantity")
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void increment() {
		this.quantity++;
	}

	@XmlAttribute(name = "total")
	public int getTotalPrice() {
		return this.product.getPrice() * this.quantity;
	}

	@Override
	public String toString() {
		return "BasketItem [product=" + this.product + ", quantity=" + this.quantity + "]";
	}

}

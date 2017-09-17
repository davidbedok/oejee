package hu.qwaevisz.webstore.ejbservice.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductStub {

	@XmlAttribute(name = "brand")
	private BrandStub brand;

	@XmlAttribute(name = "productName")
	private String name;

	@XmlAttribute(name = "price")
	private int price;

	public ProductStub() {

	}

	public ProductStub(BrandStub brand, String name, int price) {
		this.brand = brand;
		this.name = name;
		this.price = price;
	}

	public BrandStub getBrand() {
		return this.brand;
	}

	public void setBrand(BrandStub brand) {
		this.brand = brand;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.brand == null) ? 0 : this.brand.hashCode());
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
		result = prime * result + this.price;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final ProductStub other = (ProductStub) obj;
		if (this.brand != other.brand) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.price != other.price) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ProductStub [brand=" + this.brand + ", name=" + this.name + ", price=" + this.price + "]";
	}

}

package hu.qwaevisz.inventory.ejbservice.domain;

import org.codehaus.jackson.annotate.JsonProperty;

public class InternationalInventoryItemBean {

	private String label;
	private String price;

	public InternationalInventoryItemBean(String label, String price) {
		this.label = label;
		this.price = price;
	}

	@JsonProperty("product")
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@JsonProperty("price")
	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return this.label;
	}

}

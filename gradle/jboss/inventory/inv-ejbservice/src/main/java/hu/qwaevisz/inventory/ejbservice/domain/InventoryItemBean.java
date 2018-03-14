package hu.qwaevisz.inventory.ejbservice.domain;

public class InventoryItemBean {

	private String reference;
	private String name;
	private String type;
	private int value;

	public InventoryItemBean() {
		this(null, null, null, 0);
	}

	public InventoryItemBean(String reference, String name, String type, int value) {
		this.reference = reference;
		this.name = name;
		this.type = type;
		this.value = value;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "InventoryItemBean [reference=" + this.reference + ", name=" + this.name + ", type=" + this.type + ", value=" + this.value + "]";
	}

}

package hu.qwaevisz.inventory.persistence.domain;

public class Inventory {

	private String reference;
	private String name;
	private InventoryType type;
	private int value;

	public Inventory() {
		this(null, null, InventoryType.BOOK, 0);
	}

	public Inventory(String reference, String name, InventoryType type, int value) {
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

	public InventoryType getType() {
		return this.type;
	}

	public void setType(InventoryType type) {
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
		return "Inventory [reference=" + this.reference + ", name=" + this.name + ", type=" + this.type + ", value=" + this.value + "]";
	}

}

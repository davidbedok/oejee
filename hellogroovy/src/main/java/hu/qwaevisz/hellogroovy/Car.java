package hu.qwaevisz.hellogroovy;

public class Car {

	private final CarBrand brand;
	private final String model;
	private final String engineSerial;
	private final int efficiency;
	private Person owner;

	public Car(CarBrand brand, String model, String engineSerial, int efficiency) {
		this.brand = brand;
		this.model = model;
		this.engineSerial = engineSerial;
		this.efficiency = efficiency;
	}

	public Person getOwner() {
		return this.owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public CarBrand getBrand() {
		return this.brand;
	}

	public String getModel() {
		return this.model;
	}

	public String getEngineSerial() {
		return this.engineSerial;
	}

	public int getEfficiency() {
		return this.efficiency;
	}

	@Override
	public String toString() {
		return "Car [brand=" + this.brand + ", model=" + this.model + ", engineSerial=" + this.engineSerial + ", efficiency=" + this.efficiency + ", owner="
				+ this.owner + "]";
	}

}

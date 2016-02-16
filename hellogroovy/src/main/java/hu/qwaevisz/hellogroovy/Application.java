package hu.qwaevisz.hellogroovy;

public class Application {

	public static void main(String[] args) {
		final CarDealership dealerShip = CarDealership.getDatabase();
		System.out.println(dealerShip);
		final Car toyota = dealerShip.find(CarBrand.TOYOTA, "Verso");
		toyota.setOwner(new Person("Anakin", "Skywalker", "FJFEMV56", Gender.MAN));
		System.out.println(toyota);
	}

}
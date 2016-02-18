package hu.qwaevisz.hellogroovy;

public class Application {

	public static void main(String[] args) {
		final CarDealership dealerShip = CarDealership.getDatabase();
		System.out.println(dealerShip);
		final Car toyota = dealerShip.find(CarBrand.TOYOTA, "Verso");
		final Person anakin = new Person("Anakin", "Skywalker", "FJFEMV56", Gender.MAN);
		anakin.setFirstName("Darth");
		anakin.setFamilyName("Vader");
		toyota.setOwner(anakin);
		System.out.println(toyota);
	}

}
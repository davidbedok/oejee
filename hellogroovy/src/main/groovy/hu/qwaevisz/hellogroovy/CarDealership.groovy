package hu.qwaevisz.hellogroovy

class CarDealership {

	private List cars

	CarDealership() {
		this.cars = []
	}

	def add( Car car ) {
		this.cars << car
	}

	Car find( CarBrand brand, String model ) {
		this.cars.find({ it.brand == brand && it.model.startsWith(model)})
	}

	@Override
	String toString() {
		cars
	}

	static CarDealership getDatabase() {
		CarDealership cd = new CarDealership()
		cd.add( new Car(CarBrand.ROVER, "45i", "HSGFHEU35453", 91) )
		cd.add( new Car(CarBrand.TOYOTA, "Verso 1.8", "NCJ5443463", 109) )
		cd.add( new Car(CarBrand.PEAGEOT, "206", "BNCBH6434u3", 44) )
		cd.add( new Car(CarBrand.OPEL, "Astra 1.4", "CSNJWGZF674343", 88) )
		return cd
	}
}

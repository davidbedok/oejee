package hu.qwaevisz.hellogroovy

import spock.lang.Shared
import spock.lang.Specification

class CarDealershipSpec extends Specification {

    static final EFFICIENCY = 134
    static final ENGINE_SERIAL = "ABCDEFG12345"
    static final MODEL = "Supra"
    def dealership = CarDealership.getDatabase()

    def "Toyota Verso 1.8 is in the list of cars"() {
        when:
        def toyota = dealership.find(CarBrand.TOYOTA, "Verso 1.8")

        then:
        toyota.getBrand() == CarBrand.TOYOTA
        toyota.getModel() == "Verso 1.8"
    }

    def "all the expected cars are in the database (list)"() {
        when: "I search for a car based on brand and model"
        Car car = dealership.find(brand, model)

        then: "the returned car has the same brand and model"
        car.getBrand() == brand
        car.getModel() == model

        where:
        brand << [CarBrand.TOYOTA, CarBrand.OPEL, CarBrand.PEAGEOT, CarBrand.ROVER]
        model << ["Verso 1.8", "Astra 1.4", "206", "45i"]
    }

    def "all the expected cars are in the database (table)"() {
        when:
        def car = dealership.find(brand, model)

        then:
        car.getBrand() == brand
        car.getModel() == model

        where:
        brand            | model
        CarBrand.TOYOTA  | "Verso 1.8"
        CarBrand.OPEL    | "Astra 1.4"
        CarBrand.PEAGEOT | "206"
        CarBrand.ROVER   | "45i"
    }

    def "an unknown car is not in the list"() {
        expect:
        dealership.find(CarBrand.ROVER, "Unknown") == null
    }

    def "I can add and find that car"() {
        given:
        def newCar = new Car(CarBrand.TOYOTA, MODEL, ENGINE_SERIAL, EFFICIENCY)

        when: "I add a car"
        dealership.add(newCar)

        and: "search for it"
        def car = dealership.find(CarBrand.TOYOTA, MODEL)

        then: "car is not null"
        car

        and: "the set parameters are correct"
        car.getBrand() == CarBrand.TOYOTA
        car.getModel() == MODEL
        car.getEngineSerial() == ENGINE_SERIAL
        car.getEfficiency() == EFFICIENCY
    }

}

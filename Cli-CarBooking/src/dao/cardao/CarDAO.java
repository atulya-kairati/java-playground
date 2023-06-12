package dao.cardao;

import enums.Brand;
import models.car.Car;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class CarDAO {

    private Car[] availableCars;

    public CarDAO() {
        availableCars = new Car[]{
                new Car(Brand.FERRARI),
                new Car(Brand.FORD),
                new Car(Brand.MCBC),
                new Car(Brand.TATA),
                new Car(Brand.HONDA),
                new Car(Brand.TOYOTA),
                new Car(Brand.MCBC, true),
                new Car(Brand.MCBC, true),
                new Car(Brand.TOYOTA, true),
                new Car(Brand.TATA, true),
        };
    }

    public Optional<Car> getCarById(String id){
        UUID uuid = UUID.fromString(id);

        for (Car car: availableCars){
            if(car.getId().equals(uuid)) return Optional.of(car);
        }

        return Optional.empty();
    }

    public Car[] getAllCars(){
        return availableCars;
    }

    public Car[] getAllElectricCars(){
        int count = 0;
        for (Car car: availableCars){
            if(car.isElectric()) count++;
        }

        int ptr = 0;
        Car[] electricCar = new Car[count];
        for (Car car: availableCars){
            if(car.isElectric()){
                electricCar[ptr++] = car;
            }
        }

        return electricCar;
    }
}

package dao.cardao;

import enums.Brand;
import models.car.Car;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class CarDAO {

    private final List<Car> availableCars;

    public CarDAO() {
        availableCars = List.of(
                new Car(Brand.FERRARI),
                new Car(Brand.FORD),
                new Car(Brand.MCBC),
                new Car(Brand.TATA),
                new Car(Brand.HONDA),
                new Car(Brand.TOYOTA),
                new Car(Brand.MCBC, true),
                new Car(Brand.MCBC, true),
                new Car(Brand.TOYOTA, true),
                new Car(Brand.TATA, true)
        );

    }

    public Optional<Car> getCarById(String id) {
        UUID uuid = UUID.fromString(id);

        return availableCars.stream()
                .filter(car -> car.getId().equals(uuid))
                .findFirst();
    }

    public List<Car> getAllCars() {
        return availableCars;
    }

    public List<Car> getAllElectricCars() {
        return availableCars.stream()
                .filter(Car::isElectric)
                .collect(Collectors.toList());
    }
}

package service.carservice;

import models.car.Car;

import java.util.Optional;

public interface CarService {
    Optional<Car> getCarById(String id);

    Car[] getAllCars();

    Car[] getAllElectricCars();
}

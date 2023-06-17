package service.carservice;

import dao.cardao.CarDAO;
import models.car.Car;

import java.util.Optional;

public class CarServiceImpl implements CarService{

    CarDAO carDAO;

    public CarServiceImpl(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    public Optional<Car> getCarById(String id) {
        return carDAO.getCarById(id);
    }

    @Override
    public Car[] getAllCars() {
        return carDAO.getAllCars();
    }

    @Override
    public Car[] getAllElectricCars() {
        return carDAO.getAllElectricCars();
    }
}
